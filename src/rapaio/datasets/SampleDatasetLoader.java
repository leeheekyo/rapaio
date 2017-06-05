package rapaio.datasets;

import java.io.IOException;
import java.net.URISyntaxException;

import rapaio.data.Frame;
import rapaio.io.Csv;

public abstract class SampleDatasetLoader  {
	protected String resourcePath;
	
	public SampleDatasetLoader(){
		initResourcePath();
	};
	
	protected abstract Csv getDatasetConfig();
	protected abstract void initResourcePath();
	public Frame load() throws IOException, URISyntaxException {
		return getDatasetConfig().read(SampleDatasetLoader.class, resourcePath);
	}
}
