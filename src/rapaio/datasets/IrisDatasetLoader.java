package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class IrisDatasetLoader extends SampleDatasetLoader {
	@Override
	protected Csv getDatasetConfig() {
		return new Csv()
                .withDefaultTypes(VarType.NUMERIC)
                .withTypes(VarType.NOMINAL, "class");
	}
	@Override
	protected void initResourcePath() {
		resourcePath = "iris-r.csv";
	}

}
