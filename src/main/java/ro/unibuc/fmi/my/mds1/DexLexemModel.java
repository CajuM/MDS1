package ro.unibuc.fmi.my.mds1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class DexLexemModel {
	public static List<DexLexem> getByPattern(String pattern) {
		if (pattern.length() == 0) {
			return new ArrayList<DexLexem>();
		}

		Session session = Main.getSession();
		Query query = session.createQuery(
			"from DexLexem as lexem " +
			"where lexem.lexem like :state and " +
			"length(:state) = length(lexem.lexem)"
		);

		@SuppressWarnings("unchecked")
		List<DexLexem> ret = query.setParameter("state", pattern).list();
		session.close();

		return ret;
	}

	public static Character recommendLetter(List<DexLexem> lexemList, String pattern) {
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '_') {
				indexes.add(i);
			}
		}

		if (lexemList.isEmpty() || indexes.isEmpty()) {
			return 'a';
		}

		Map<Character, Integer> scores = new HashMap<Character, Integer>();
		for (DexLexem lexem : lexemList) {
			for (int i : indexes) {
				char currentLetter = lexem.lexem.charAt(i);
				Integer currentScore = scores.get(currentLetter);
				currentScore = currentScore == null ? 1 : currentScore + 1;
				scores.put(currentLetter, currentScore);
			}
		}

		char ret = 'a';
		int maxScore = 0;

		for (Map.Entry<Character, Integer> entry : scores.entrySet()) {
			int currentScore = entry.getValue();
			if (currentScore > maxScore) {
				ret = entry.getKey();
				maxScore = currentScore;
			}
		}

		return ret;
	}
}
