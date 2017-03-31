package ro.unibuc.fmi.my.mds1;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class DexLexem implements Serializable {
	public Integer id;
	public String lexem;
	public Set<DexDefinition> definitions = new HashSet<DexDefinition>(0);
}
