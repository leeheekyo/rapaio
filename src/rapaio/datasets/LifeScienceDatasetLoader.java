package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class LifeScienceDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withDefaultTypes(VarType.NUMERIC)
                .withTypes(VarType.NOMINAL, "class");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "life_science.csv";
	}

}
