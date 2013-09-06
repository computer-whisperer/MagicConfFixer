package Christian.ConfFixer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfFixer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			String currentDir = new File("").getAbsolutePath();
			System.out.println(currentDir);			
			String srcString = currentDir + "/" + args[0] +"/magic/MagicLauncher.cfg";
			String bakString = srcString + ".bak";
			Path srcpath = Paths.get(srcString);
			Path bakpath = Paths.get(bakString);
			copyFile(new File(srcString), new File(bakString));
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(bakpath), charset);
			content = content.replaceAll("\".*"+args[1], "\"" + currentDir.replaceAll(":", ":/"));
			content = content.replaceAll("\\\\", "/");
			Files.write(srcpath, content.getBytes(charset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
public static void copyFile(File sourceFile, File destFile) throws IOException {
		
	    if(!destFile.exists()) {
	    	destFile.getParentFile().mkdirs();
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
}