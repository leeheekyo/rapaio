package rapaio.datasets;

import rapaio.data.VarType;
import rapaio.io.Csv;

public class PlayDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withHeader(true)
                .withQuotes(false)
                .withTypes(VarType.NUMERIC, "temp", "humidity")
                .withTypes(VarType.NOMINAL, "windy");
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "play.csv";
	}

}
