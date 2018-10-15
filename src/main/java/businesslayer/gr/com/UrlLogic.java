package businesslayer.gr.com;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import dao.gr.com.UrlEntityDao;
import entity.gr.com.UrlEntity;

public class UrlLogic {
	// storage for generated keys
		private HashMap<String, String> keyMap; // key-url map
		private HashMap<String, String> valueMap;// url-key map to quickly check
													// whether an url is
		// already entered in our system
		private String domain; // Use this attribute to generate urls for a custom
								// domain name defaults to http://fkt.in
		private char myChars[]; // This array is used for character to number
								// mapping
		private Random myRand; // Random object used to generate random integers
		private int keyLength; // the key length in URL defaults to 8

		// Default Constructor
		UrlLogic() {
			
			keyMap = new HashMap<String, String>();
			valueMap = new HashMap<String, String>();
			
			UrlEntityDao urlEntityDao=new UrlEntityDao();
			 List<UrlEntity> urlentries=  urlEntityDao.getAllUrl();
			
			for (int i = 0; i < urlentries.size(); i++) {
				keyMap.put(urlentries.get(i).getShortname(),urlentries.get(i).getFullname());
				valueMap.put(urlentries.get(i).getFullname(),urlentries.get(i).getShortname());
			}
			
			
			myRand = new Random();
			keyLength = 5;
			myChars = new char[62];
			for (int i = 0; i < 62; i++) {
				int j = 0;
				if (i < 10) {
					j = i + 48;
				} else if (i > 9 && i <= 35) {
					j = i + 55;
				} else {
					j = i + 61;
				}
				myChars[i] = (char) j;
			}
			domain = "http://localhost:8080/urlservice/api/url/hiturl";
		}

		// Constructor which enables you to define tiny URL key length and base URL
		// name
		UrlLogic(int length, String newDomain) {
			this();
			this.keyLength = length;
			if (!newDomain.isEmpty()) {
				newDomain = sanitizeURL(newDomain);
				domain = newDomain;
			}
		}

		// shortenURL
		// the public method which can be called to shorten a given URL
		public String shortenURL(String longURL) {
			String shortURL = "";
			if (validateURL(longURL)) {
				longURL = sanitizeURL(longURL);
				if (valueMap.containsKey(longURL)) {
					shortURL = valueMap.get(longURL);
				} else {
					shortURL = domain + "/" + getKey(longURL);
				}
			}
			// add http part
			return shortURL;
		}
		
		//get full long url from key
		public String getFullUrl(String shortURL)
		{ 	shortURL=domain+"/"+shortURL;
			if (keyMap.containsKey(shortURL)) {
				return keyMap.get(shortURL);
			} else {
				return null;
			}
		}
		
		//get full short url from key
		public String getShortUrl(String shortURL)
		{ 	
			return shortURL=domain+"/"+shortURL;
		}
		
		//check if url present already at adding
		public String checkPresent(String longURL)
		{
			if (valueMap.containsKey(longURL)) {
				return valueMap.get(longURL);
			} else {
				return null;
			}
		}
		
		// expandURL
		// public method which returns back the original URL given the shortened url
		public String expandURL(String shortURL) {
			String longURL = "";
			String key = shortURL.substring(domain.length() + 1);
			longURL = keyMap.get(key);
			return longURL;
		}

		// Validate URL
		// not implemented, but should be implemented to check whether the given URL
		// is valid or not
		boolean validateURL(String url) {
			return true;
		}

		// sanitizeURL
		// This method should take care various issues with a valid url
		// e.g. www.google.com,www.google.com/, http://www.google.com,
		// http://www.google.com/
		// all the above URL should point to same shortened URL
		// There could be several other cases like these.
		String sanitizeURL(String url) {
			if (url.substring(0, 7).equals("http://"))
				url = url.substring(7);

			if (url.substring(0, 8).equals("https://"))
				url = url.substring(8);

			if (url.charAt(url.length() - 1) == '/')
				url = url.substring(0, url.length() - 1);
			return url;
		}

		/*
		 * Get Key method
		 */
		private String getKey(String longURL) {
			String key;
			key = generateKey();
			keyMap.put(key, longURL);
			valueMap.put(longURL, key);
			return key;
		}

		//get browser
		public String getBrowser(String userAgent)
		{
		    String browser = null;
		    if (userAgent.toLowerCase().contains( "firefox") != false ) {
	            browser = "Firefox";
	        } else if ( userAgent.toLowerCase().contains( "msie") != false ) {
	        	browser = "IE";
	        } else if (  userAgent.toLowerCase().contains( "trident") != false ) {
	        	browser =  "IE";
	        } else if (  userAgent.toLowerCase().contains( "chrome") != false ) {
	        	browser =  "Chrome";
	        } else if (  userAgent.toLowerCase().contains( "safari") != false ) {
	        	browser =  "Safari";
	        } else {
	        	browser="other";
	        }
		    return browser;
		}
		
		//get browser
		public String getOS(String userAgent)
		{
		    String opSys = null;
		    if (userAgent.toLowerCase().contains( "windows") != false ) {
		    	opSys = "Windows";
	        } else if ( userAgent.toLowerCase().contains( "linux") != false ) {
	        	opSys = "Linux";
	        } else if (  userAgent.toLowerCase().contains( "iphone") != false ) {
	        	opSys =  "IOS";
	        }  else if (  userAgent.toLowerCase().contains( "macintosh") != false ) {
	        	opSys =  "Macintosh";
	        } else if (  userAgent.toLowerCase().contains( "android") != false ) {
	        	opSys =  "Android";
	        } else {
	        	opSys="other";
	        }
		    return opSys;
		}		
		
		// generateKey
		private String generateKey() {
			String key = "";
			boolean flag = true;
			while (flag) {
				key = "";
				for (int i = 0; i <= keyLength; i++) {
					key += myChars[myRand.nextInt(62)];
				}
				// System.out.println("Iteration: "+ counter + "Key: "+ key);
				if (!keyMap.containsKey(key)) {
					flag = false;
				}
			}
			return key;
		}
	

}
