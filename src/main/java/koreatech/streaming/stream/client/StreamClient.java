package koreatech.streaming.stream.client;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.stream.common.VlcjCommon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
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
    private static OrchidService orchidService = new OrchidService();
    public static final String REST_REGISTRAR_URI = "http://localhost:8100/registrar";

    public static void main(String[] args) throws Exception {
        if (args.length < 0) {
            System.out.println("Specify a single media URL");
            System.exit(1);
        }
        RestTemplate restTemplate = new RestTemplate();

        //클라이언트 info Update
        String orchid2 = orchidService.getOrchidIPv4Addr("10.1.7.2");
        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(REST_REGISTRAR_URI + "/registration?contextId=" + OrchidService.contextIdForIPv4Addr
                + "&name=10.1.7.2"
                + "&orchid=" + orchid2
                + "&locator=10.1.7.2"
                + "&scheme=*", String.class);
        System.out.println(responseEntity2);

        //프로그램 실행시 기본정보를 registrar에서 불러옴
        String contentName = "multifia/Spiderman.mp4";
        String orchid = orchidService.getOrchidContentName(contentName);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_REGISTRAR_URI + "/lookup/" + orchid, String.class);
        String lookupResult = responseEntity.getBody();

        System.out.println("lookupResult = " + lookupResult);

        String[] result = lookupResult.split("#");
        System.out.println(result[0]);
        System.out.println(result[1]);
        String[] target = result[1].split(":"); //target address & port

        String streamUrl = result[0] + "://" + result[1];

        String[] libvlcArgs = new String[3];
        libvlcArgs[0] = result[0];
        libvlcArgs[1] = target[0];
        libvlcArgs[2] = target[1];
        System.out.println(contentName);
        System.out.println(streamUrl);

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
                JButton regitButton = new JButton("Registration");
                JButton startButton = new JButton("Start");
                JButton stopButton = new JButton("Stop");
                JButton pauseButton = new JButton("pause");
                JButton resumeButton = new JButton("resume");
                JButton highButton = new JButton("High");
                JButton lowButton = new JButton("Low");
                JButton mediumButton = new JButton("Medium");

                regitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //클라이언트 정보 등록
                           RestTemplate restTemplate = new RestTemplate();
                            String orchid2 = orchidService.getOrchidIPv4Addr("10.1.7.2");
                            ResponseEntity<String> responseEntity2 = restTemplate.getForEntity("http://127.0.0.1:8100/registrar" + "/registration?contextId=1AF52BA93BA24026CAF34D783DC12A09"
                                    + "&name=10.1.7.2"
                                    + "&orchid=" + orchid2
                                    + "&locator=10.1.7.2"
                                    + "&scheme=*", String.class);
                            System.out.println(responseEntity2);
                        } catch (Exception x) {}
                    }
                });

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

                pauseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.pauseStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                resumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.resumeStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                highButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.highStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                lowButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.lowStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                mediumButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            streamClientRestController.mediumStream(contentName);
                        } catch (Exception x) {
                        }
                    }
                });

                //imagePane = new ImagePane(image);
                //imagePane.setSize(width, height);
                //imagePane.setMinimumSize(new Dimension(width, height));
                //imagePane.setPreferredSize(new Dimension(width, height));
                //frame.getContentPane().setLayout(new BorderLayout());
                //frame.getContentPane().add(imagePane, BorderLayout.CENTER);

                panel.add(regitButton);
                panel.add(startButton);
                panel.add(stopButton);
                panel.add(pauseButton);
                panel.add(resumeButton);
                panel.add(highButton);
                panel.add(lowButton);
                panel.add(mediumButton);
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
