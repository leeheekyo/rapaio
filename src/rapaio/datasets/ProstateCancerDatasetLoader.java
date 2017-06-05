package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class ProstateCancerDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar('\t')
                .withDefaultTypes(VarType.NUMERIC, VarType.NOMINAL);
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "prostate.csv";
	}

}
