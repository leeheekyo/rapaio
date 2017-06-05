package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class HousingDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withDefaultTypes(VarType.NUMERIC);
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "housing.csv";
	}

}
