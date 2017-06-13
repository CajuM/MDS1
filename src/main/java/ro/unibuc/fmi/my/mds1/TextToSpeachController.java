package ro.unibuc.fmi.my.mds1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToSpeachController {
	public class Say {
		private final String what;
		private final byte[] wav;

		public Say(String what) {
			this.what = what;
			this.wav = new byte[] {(byte) 0xFF};
		}
		
		public String getWhat() {
			return this.what;
		}
		
		public byte[] getWav() {
			return this.wav;
		}
	}

	@RequestMapping("/api/say")
	public Say greeting(@RequestParam(value = "what", defaultValue = "En archē ēn ho Lógos") String what) {
		return new Say(what);
	}
}
