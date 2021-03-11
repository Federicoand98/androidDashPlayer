package jLibdash.dash.mpd;

import java.util.Vector;

public interface IRepresentationBase extends IMPDElement {
	
	public Vector<IDescriptor> getFramePacking();
	public Vector<IDescriptor> getAudioChannelConfiguration();
	public Vector<IDescriptor> getContentProtection();
	public Vector<String> getProfiles();
	public int getWidth();
	public int getHeight();
	public String getSar();
	public String getFrameRate();
	public String getAudioSamplingRate();
	public String getMimeType();
	public Vector<String> getSegmentProfiles();
	public Vector<String> getCodecs();
	public double getMaximumSAPPeriod();
	public byte getStartWithSAP();
	public double getMaxPlayoutRate();
	public boolean hasCodingDependency();
	public String getScanType();
	
	public int hashCode();
	public boolean equals(Object obj);

}
