package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class ILSAdvertisingDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withQuotes(true)
                .withDefaultTypes(VarType.NUMERIC)
                .withTypes(VarType.NOMINAL, "ID");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "ISL/advertising.csv";
	}

}
