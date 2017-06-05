package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class CarMpgDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withHeader(true)
                .withQuotes(true)
                .withDefaultTypes(VarType.NUMERIC)
                .withTypes(VarType.NOMINAL, "carname");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "carmpg.csv";
	}

}
