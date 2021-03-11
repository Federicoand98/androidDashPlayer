package jLibdash.dash.helpers;

import java.util.Optional;

public class PathUtils {
	
	public final static char FILE_SEPARATOR = '/';
	
	public static String combinePath(String path1, String path2) {
		if(path2.startsWith("..")) {
			if(path1.lastIndexOf('/') == path1.length() - 1) {
				String realPath1 = path1.substring(0, path1.lastIndexOf('/'));
				return combinePath(
						realPath1.substring(0, realPath1.lastIndexOf('/')),
						path2.substring(path2.indexOf('/') + 1));
			}
			
			return combinePath(
					path1.substring(0, path1.lastIndexOf('/')),
					path2.substring(path2.indexOf('/') + 1));
		}
		
		if(path1.equals(""))
			return path2;
		if(path2.equals(""))
			return path1;
		
		
		char path1Last = path1.charAt(path1.length()-1);
		char path2First = path2.charAt(0);
		
		if(path1Last == FILE_SEPARATOR && path2First == FILE_SEPARATOR)
			return path1 + path2.substring(1, path2.length());
		
		if(path1Last != FILE_SEPARATOR && path2First != FILE_SEPARATOR)
			return path1 + FILE_SEPARATOR + path2;
		
		return path1 + path2;
	}
	
	public static Optional<ObjectUrl> getHostPortAndPath(String url) {
		String hostPort = "";
		int found = 0;
		int pathBegin;
		int port = -1;
		
		if(!url.startsWith("http://") && !url.startsWith("https://"))
			return Optional.empty();
		
		if(url.substring(0, 7).equals("http://") || url.substring(0, 8).equals("https://") ) {
			found = url.indexOf("//");
			pathBegin = url.indexOf("/", found+2);
			String path = "";
			if(pathBegin!=-1 && pathBegin < (url.length()-1)) {
				path = url.substring(pathBegin);
				hostPort = url.substring(found+2, pathBegin);
			}
			else
				hostPort = url.substring(found+2);
			
			found = hostPort.indexOf(":");
			String host = "";
			if(found != -1 ) {
				port = Integer.parseInt(hostPort.substring(found+1));
				host = hostPort.substring(0, found);
			} else
				host = hostPort;
			if ((host.length() > 0) && (path.length() > 0)) {
				ObjectUrl objUrl = new ObjectUrl(host, port, path);
				return Optional.of(objUrl);
				
			}
		}
		
		return Optional.empty();
	}
	
	public static Optional<ObjectRange> getStartAndEndBytes(String byteRange) {
		int found = 0;
		ObjectRange objRange;
		
		found = byteRange.indexOf("-");
		if(byteRange.matches("[0-9]+\\-[0-9]+")) {
			objRange = new ObjectRange(Integer.parseInt(byteRange.substring(0, found)),
					Integer.parseInt(byteRange.substring(found+1)));
			if(objRange.getStartByte() <= objRange.getEndByte())
				return Optional.of(objRange);
		}
		
		return Optional.empty();
	}
	
	public static String getDirectoryPath(String path) {
		int pos = 0;
		
		try {
			if(path.substring(0, 7).equals("http://") || path.substring(0, 8).equals("https://") ) {
				pos = path.lastIndexOf("/");
			} else {
				pos = path.lastIndexOf(FILE_SEPARATOR);
			}
		} catch(StringIndexOutOfBoundsException sioobe) {
			pos = path.lastIndexOf(FILE_SEPARATOR);
		}
		
		if(pos >= 0)
			return path.substring(0, pos);
		return path;
	}

}
