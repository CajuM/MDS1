package ro.unibuc.fmi.my.mds1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HangManController {
	private class HangmanResp {
		public String status;
		public List<String> foundWords;
		public Character recommendedLetter;
	}

	@RequestMapping("/api/hangMan")
	public HangmanResp hangMan(@RequestParam(value = "state", defaultValue = "") String state) {
		HangmanResp ret = new HangmanResp();

		if (state.length() == 0) {
			ret.status = "fail";
			return ret;
		}

		List<DexLexem> lexems = DexLexemModel.getByPattern(state);
		if (lexems.isEmpty()) {
			ret.status = "nothing found";
			return ret;
		}

		ret.foundWords = lexems.stream()
			.map(lexem -> lexem.lexem)
			.collect(Collectors.toList());

		ret.recommendedLetter = DexLexemModel.recommendLetter(lexems, state);

		return ret;
	}
}
