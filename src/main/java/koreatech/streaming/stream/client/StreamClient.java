package koreatech.streaming.stream.client;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.stream.common.VlcjCommon;
import uk.co.caprica.vlcj.binding.LibC;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * This simple test player shows how to get direct access to the video frame data.
 * <p>
 * This implementation uses the new (1.1.1) libvlc video call-backs function.
 * <p>
 * Since the video frame data is made available, the Java call-back may modify the contents of the
 * frame if required.
 * <p>
 * The frame data may also be rendered into components such as an OpenGL texture.
 */
public class StreamClient extends VlcjCommon {
    // The size does NOT need to match the mediaPlayer size - it's the size that
    // the media will be scaled to
    // Matching the native size will be faster of course
    private final int width = 720;

    private final int height = 480;

    // private final int width = 1280;
    // private final int height = 720;

    /**
     * Image to render the video frame data.
     */
    private final BufferedImage image;

    private final MediaPlayerFactory factory;

    private final DirectMediaPlayer mediaPlayer;

    private ImagePane imagePane;

    private static StreamClientRestController streamClientRestController = new StreamClientRestController();

    public static void main(String[] args) throws Exception {
        LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "/Program Files/VideoLAN/VLC/plugins", 1);

        if (args.length < 4) {
            System.out.println("Specify a single media URL");
            System.exit(1);
        }

        //Rest Start!
        //String contentName = args[0];
        //streamClientRestController.startStream(contentName);

        String streamUrl = args[1] + "://" + args[2] + ":" + args[3];

        String contentName = args[0];
        String[] libvlcArgs = new String[3];
        libvlcArgs[0] = args[1];
        libvlcArgs[1] = args[2];
        libvlcArgs[2] = args[3];

        new StreamClient(streamUrl, libvlcArgs, contentName);
        // Application will not exit since the UI thread is running
    }

    public StreamClient(String media, String[] args, String contentName) throws InterruptedException, InvocationTargetException, Exception {
        image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height);
        image.setAccelerationPriority(1.0f);

        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("VLCJ Direct Video Player");
                //frame.setIconImage(new ImageIcon(getClass().getResource("/icons/vlcj-logo.png")).getImage());
                //버튼 추가
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLACK);
                JButton startButton = new JButton("Start");
                JButton stopButton = new JButton("Stop");

                startButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.startStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                stopButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.stopStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                imagePane = new ImagePane(image);
                imagePane.setSize(width, height);
                imagePane.setMinimumSize(new Dimension(width, height));
                imagePane.setPreferredSize(new Dimension(width, height));
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(imagePane, BorderLayout.CENTER);

                panel.add(startButton);
                panel.add(stopButton);
                frame.add(panel, BorderLayout.SOUTH);

                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent evt) {
                        mediaPlayer.release();
                        factory.release();
                        System.exit(0);
                    }
                });
            }

        });

        factory = new MediaPlayerFactory(args);
        mediaPlayer = factory.newDirectMediaPlayer(new TestBufferFormatCallback(), new TestRenderCallback());
        mediaPlayer.playMedia(media);

        // Just to show regular media player functions still work...
        Thread.sleep(5000);
        mediaPlayer.nextChapter();
    }


    private final class ImagePane extends JPanel {

        private final BufferedImage image;

        private final Font font = new Font("Sansserif", Font.BOLD, 36);

        public ImagePane(BufferedImage image) {
            this.image = image;
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(image, null, 0, 0);
            // You could draw on top of the image here...
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //g2.setColor(Color.red);
            //g2.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            //g2.fillRoundRect(100, 100, 100, 80, 32, 32);
            //g2.setComposite(AlphaComposite.SrcOver);
            //g2.setColor(Color.white);
            //g2.setFont(font);
            //g2.drawString("vlcj direct media player", 130, 150);
        }
    }

    private final class TestRenderCallback extends RenderCallbackAdapter {

        public TestRenderCallback() {
            super(((DataBufferInt) image.getRaster().getDataBuffer()).getData());
        }

        @Override
        public void onDisplay(DirectMediaPlayer mediaPlayer, int[] data) {
            // The image data could be manipulated here...

            /* RGB to GRAYScale conversion example */
//            for(int i=0; i < data.length; i++){
//                int argb = data[i];
//                int b = (argb & 0xFF);
//                int g = ((argb >> 8 ) & 0xFF);
//                int r = ((argb >> 16 ) & 0xFF);
//                int grey = (r + g + b + g) >> 2 ; //performance optimized - not real grey!
//                data[i] = (grey << 16) + (grey << 8) + grey;
//            }
            imagePane.repaint();
        }
    }

    private final class TestBufferFormatCallback implements BufferFormatCallback {
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
            return new RV32BufferFormat(width, height);
        }

    }
}
