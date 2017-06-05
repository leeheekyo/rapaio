package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class SpamBaseDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv().withDefaultTypes(VarType.NUMERIC)
                .withTypes(VarType.NOMINAL, "spam");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "spam-base.csv";
	}

}
