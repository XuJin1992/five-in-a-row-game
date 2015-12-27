
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class chessmusic {
    private Clip clip;
	public chessmusic(String What)
	{
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(chessimage.path+What));
		     clip = AudioSystem.getClip();
			clip.open(inputStream);
			if(What=="ÏÂÆå.wav")clip.start();
			else if(What=="È¡Ê¤.wav")clip.start();
			else clip.loop(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Stop()
	{
		clip.stop();
		clip.close();
		}
}