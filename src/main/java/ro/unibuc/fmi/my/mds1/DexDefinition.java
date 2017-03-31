package ro.unibuc.fmi.my.mds1;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class DexDefinition implements Serializable {
	private Integer id;
	private String definition;
	private Set<DexLexem> lexems = new HashSet<DexLexem>(0);
}
