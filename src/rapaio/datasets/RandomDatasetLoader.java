package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class RandomDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withTypes(VarType.BINARY, "bin")
                .withTypes(VarType.INDEX, "index")
                .withTypes(VarType.NUMERIC, "num")
                .withTypes(VarType.STAMP, "stamp")
                .withTypes(VarType.NOMINAL, "nom");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "random.csv";
	}

}
