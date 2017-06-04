package rapaio.printer;

import rapaio.data.Var;
import rapaio.data.VarType;

public class TypeStrategyFactory {
	public static TypeStrategy getTypeStrategyFactory(Var v){
		TypeStrategy typeStrategy = null;
		if (v.getType() == VarType.BINARY) {
            typeStrategy = new BinaryTypeStrategy();
        }

        if (v.getType() == VarType.INDEX || v.getType() == VarType.NUMERIC) {
            typeStrategy = new NumbericTypeStrategy();
        }

        if (v.getType().isNominal()) {
            typeStrategy = new NominalTypeStrategy();
        }
        return typeStrategy;
	}
}
