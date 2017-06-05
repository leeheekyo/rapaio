package rapaio.datasets;

import java.io.IOException;
import java.net.URISyntaxException;

import rapaio.data.Frame;
import rapaio.data.VarType;
import rapaio.io.Csv;

public class ChestDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withQuotes(true)
                .withDefaultTypes(VarType.NUMERIC);
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "chest.csv";
	}

}
