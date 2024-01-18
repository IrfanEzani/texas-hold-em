 package poker;

 import java.awt.Graphics;
 import java.awt.Image;
 import java.awt.MediaTracker;
 import java.awt.Panel;
 import java.awt.Toolkit;
 import java.awt.image.BufferedImage;
 import java.awt.image.ImageObserver;
 import java.net.URL;
 import java.util.HashMap;
 
 public class ImageLoader {
    private static HashMap cache = new HashMap();
 
    public static synchronized BufferedImage getImage(String f) {
       BufferedImage img = (BufferedImage)cache.get(f);
       if (img == null) {
          img = loadImage(f);
          cache.put(f, img);
       }
 
       return img;
    }
 
    public static synchronized BufferedImage getImage(URL u) {
       BufferedImage img = (BufferedImage)cache.get(u);
       if (img == null) {
          img = loadImage(u);
          cache.put(u, img);
       }
 
       return img;
    }
 
    private static BufferedImage loadImage(String imageName) {
       Image origImage = Toolkit.getDefaultToolkit().getImage(imageName);
       return loadImage(origImage);
    }
 
    private static BufferedImage loadImage(URL imageURL) {
       Image origImage = Toolkit.getDefaultToolkit().getImage(imageURL);
       return loadImage(origImage);
    }
 
    private static BufferedImage loadImage(Image origImage) {
       try {
          MediaTracker tracker = new MediaTracker(new Panel());
          tracker.addImage(origImage, 0);
          tracker.waitForID(0);
          if (tracker.statusID(0, true) != 8) {
             throw new RuntimeException("Unable to load image");
          }
       } catch (InterruptedException var5) {
       }
 
       int imageWidth = origImage.getWidth((ImageObserver)null);
       int imageHeight = origImage.getHeight((ImageObserver)null);
       BufferedImage buf = new BufferedImage(imageWidth, imageHeight, 1);
       Graphics g = buf.createGraphics();
       g.drawImage(origImage, 0, 0, (ImageObserver)null);
       return buf;
    }
 }
 