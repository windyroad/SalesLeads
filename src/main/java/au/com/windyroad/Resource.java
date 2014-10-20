package au.com.windyroad;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

public class Resource implements Serializable {

	private static final long serialVersionUID = -3005947385105850799L;

	private String uri;

	public Resource(String uri) throws FileNotFoundException {
		this.uri = uri;
		if (getClass().getResourceAsStream(this.uri) == null) {
			throw new FileNotFoundException(this.uri);
		}
	}

	public byte[] getContents() throws IOException {
		InputStream is = getClass().getResourceAsStream(uri);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		if (is != null) {

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();

		}
		return buffer.toByteArray();
	}

	private long getRawModificationTime() throws IOException {
		URLConnection conn = getClass().getResource(uri).openConnection();
		long modificationTime = conn.getLastModified();
		conn.getInputStream().close();
		return modificationTime;
	}

	public Date getModificationTime() throws IOException {
		try {
		Calendar c = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		c.setTimeInMillis(getRawModificationTime());
		return c.getTime();
		}
		catch(Throwable t) {
			Logger.getLogger("bw.logger").error("Error getting modification time", t);
			throw t;
		}
	}

	private static MimetypesFileTypeMap mimeMap = initMimeMap();

	public String getMimeType() {
		return mimeMap.getContentType(getName());
	}

	public String getName() {
		return uri;
	}

	public String getURI() {
		return uri;
	}

	private static MimetypesFileTypeMap initMimeMap() {
		MimetypesFileTypeMap rval = new MimetypesFileTypeMap();
		rval.addMimeTypes("text/css css CSS");
		rval.addMimeTypes("text/html html HTML");
		rval.addMimeTypes("application/javascript js JS j J sj SJ");
		rval.addMimeTypes("image/x-icon ico ICO");
		rval.addMimeTypes("application/cib cib CIB");
		return rval;
	}
}