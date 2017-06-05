package rapaio.datasets;

import rapaio.io.Csv;

public class MushroomsDatasetLoader extends SampleDatasetLoader {

	@Override
	protected Csv getDatasetConfig() {
        return new Csv()
                .withSeparatorChar(',')
                .withHeader(true)
                .withQuotes(false);
	}

	@Override
	protected void initResourcePath() {
		resourcePath = "mushrooms.csv";
	}

}
