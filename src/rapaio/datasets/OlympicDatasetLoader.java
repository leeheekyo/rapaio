package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class OlympicDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withQuotes(false)
                .withTypes(VarType.NUMERIC, "Edition");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "olympic.csv";
	}

}
