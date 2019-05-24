package me.favasian.vidtoimg;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.FrameGrabber.Exception;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class convertMovietoJPG {
    private int frameJump;
    private String mp4Path, imgPath, imgType;
    public convertMovietoJPG(String mp4Path, String imgPath, String imgType,int frameJump){
        this.mp4Path = mp4Path;
        this.imgPath = imgPath;
        this.imgType = imgType;
        this.frameJump = frameJump;
    }

    public void vidimg()throws IOException{
        Java2DFrameConverter converter = new Java2DFrameConverter();
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path);

        Frame frame;
        int imgNum = 0;

        frameGrabber.start();

        System.out.println("Video has " + frameGrabber.getLengthInFrames() + " frames and has frame rate of "+ frameGrabber.getFrameRate());

        try {
            for(int i=frameJump;i<=frameGrabber.getLengthInFrames();i+=frameJump){
                imgNum++;
                frameGrabber.setFrameNumber(i);
                frame = frameGrabber.grab();
                BufferedImage bi = converter.convert(frame);
                if(bi != null){
                    String path = imgPath + File.separator + imgNum + ".jpg";
                    ImageIO.write(bi, imgType, new File(path));
                }
            }
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finished");
    }
}
