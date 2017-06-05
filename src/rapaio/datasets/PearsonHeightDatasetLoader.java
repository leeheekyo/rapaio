package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class PearsonHeightDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withDefaultTypes(VarType.NUMERIC);
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "pearsonheight.csv";
	}

}
