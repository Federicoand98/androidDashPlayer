package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;


public class RepresentationBase extends AbstractMPDElement implements IRepresentationBase {

	private Vector<IDescriptor> framePacking;
	private Vector<IDescriptor> audioChannelConfiguration;
	private Vector<IDescriptor> contentProtection;
	private Vector<String> profiles;
	private int width;
	private int height;
	private String sar;
	private String frameRate;
	private String audioSamplingRate;
	private String mimeType;
	private Vector<String> segmentProfiles;
	private Vector<String> codecs;
	private double maximumSAPPeriod;
	private byte startWithSAP;
	private double maxPlayoutRate;
	private boolean codingDependency;
	private String scanType;
	
	public RepresentationBase() {
		super();
		framePacking = new Vector<IDescriptor>();
		audioChannelConfiguration = new Vector<IDescriptor>();
		contentProtection = new Vector<IDescriptor>();
		width = 0;
		height = 0;
		maximumSAPPeriod = 0;
		startWithSAP = 0;
		maxPlayoutRate = 0.0;
		codingDependency = false;
	}
	

	public Vector<IDescriptor> getFramePacking() {
		return this.framePacking;
	}
	
	public void addFramePacking(Descriptor framePacking) {
		this.framePacking.add(framePacking);
	}

	public Vector<IDescriptor> getAudioChannelConfiguration() {
		return this.audioChannelConfiguration;
	}
	
	public void addAudioChannelConfiguration(Descriptor audioChannelConfiguration) {
		this.audioChannelConfiguration.add(audioChannelConfiguration);
	}

	public Vector<IDescriptor> getContentProtection() {
		return this.contentProtection;
	}
	
	public void addContentProtection(Descriptor contentProtection) {
		this.contentProtection.add(contentProtection);
	}

	public Vector<String> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(String profiles) {
		this.profiles = VectorUtils.toVector(profiles.split(","));
	}
	
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public String getSar() {
		return this.sar;
	}
	
	public void setSar(String sar) {
		this.sar = sar;
	}

	public String getFrameRate() {
		return this.frameRate;
	}
	
	public void setFrameRate(String frameRate) {
		this.frameRate = frameRate;
	}

	public String getAudioSamplingRate() {
		return this.audioSamplingRate;
	}
	
	public void setAudioSamplingRate(String audioSamplingRate) {
		this.audioSamplingRate = audioSamplingRate;
	}

	public String getMimeType() {
		return this.mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Vector<String> getSegmentProfiles() {
		return this.segmentProfiles;
	}
	
	public void setSegmentProfiles(String segmentProfiles) {
		this.segmentProfiles = VectorUtils.toVector(segmentProfiles.split(","));
	}

	public Vector<String> getCodecs() {
		return this.codecs;
	}
	
	public void setCodecs(String codecs) {
		this.codecs = VectorUtils.toVector(codecs.split(","));
	}

	public double getMaximumSAPPeriod() {
		return this.maximumSAPPeriod;
	}
	
	public void setMaximumSAPPeriod(double maximumSAPPeriod) {
		this.maximumSAPPeriod = maximumSAPPeriod;
	}

	public byte getStartWithSAP() {
		return this.startWithSAP;
	}
	
	public void setStartWithSAP(byte startWithSAP) {
		this.startWithSAP = startWithSAP;
	}

	public double getMaxPlayoutRate() {
		return this.maxPlayoutRate;
	}
	
	public void setMaxPlayoutRate(double maxPlayoutRate) {
		this.maxPlayoutRate = maxPlayoutRate;
	}


	public boolean hasCodingDependency() {
		return this.codingDependency;
	}
	
	public void setCodingDependency(boolean codingDependency) {
		this.codingDependency = codingDependency;
	}

	public String getScanType() {
		return this.scanType;
	}
	
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((audioChannelConfiguration == null) ? 0 : audioChannelConfiguration.hashCode());
		result = prime * result + ((audioSamplingRate == null) ? 0 : audioSamplingRate.hashCode());
		result = prime * result + ((codecs == null) ? 0 : codecs.hashCode());
		result = prime * result + (codingDependency ? 1231 : 1237);
		result = prime * result + ((contentProtection == null) ? 0 : contentProtection.hashCode());
		result = prime * result + ((framePacking == null) ? 0 : framePacking.hashCode());
		result = prime * result + ((frameRate == null) ? 0 : frameRate.hashCode());
		result = prime * result + height;
		long temp;
		temp = Double.doubleToLongBits(maxPlayoutRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maximumSAPPeriod);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((profiles == null) ? 0 : profiles.hashCode());
		result = prime * result + ((sar == null) ? 0 : sar.hashCode());
		result = prime * result + ((scanType == null) ? 0 : scanType.hashCode());
		result = prime * result + ((segmentProfiles == null) ? 0 : segmentProfiles.hashCode());
		result = prime * result + startWithSAP;
		result = prime * result + width;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepresentationBase other = (RepresentationBase) obj;
		if (audioChannelConfiguration == null) {
			if (other.audioChannelConfiguration != null)
				return false;
		} else if (!audioChannelConfiguration.equals(other.audioChannelConfiguration))
			return false;
		if (audioSamplingRate == null) {
			if (other.audioSamplingRate != null)
				return false;
		} else if (!audioSamplingRate.equals(other.audioSamplingRate))
			return false;
		if (codecs == null) {
			if (other.codecs != null)
				return false;
		} else if (!codecs.equals(other.codecs))
			return false;
		if (codingDependency != other.codingDependency)
			return false;
		if (contentProtection == null) {
			if (other.contentProtection != null)
				return false;
		} else if (!contentProtection.equals(other.contentProtection))
			return false;
		if (framePacking == null) {
			if (other.framePacking != null)
				return false;
		} else if (!framePacking.equals(other.framePacking))
			return false;
		if (frameRate == null) {
			if (other.frameRate != null)
				return false;
		} else if (!frameRate.equals(other.frameRate))
			return false;
		if (height != other.height)
			return false;
		if (Double.doubleToLongBits(maxPlayoutRate) != Double.doubleToLongBits(other.maxPlayoutRate))
			return false;
		if (Double.doubleToLongBits(maximumSAPPeriod) != Double.doubleToLongBits(other.maximumSAPPeriod))
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		if (profiles == null) {
			if (other.profiles != null)
				return false;
		} else if (!profiles.equals(other.profiles))
			return false;
		if (sar == null) {
			if (other.sar != null)
				return false;
		} else if (!sar.equals(other.sar))
			return false;
		if (scanType == null) {
			if (other.scanType != null)
				return false;
		} else if (!scanType.equals(other.scanType))
			return false;
		if (segmentProfiles == null) {
			if (other.segmentProfiles != null)
				return false;
		} else if (!segmentProfiles.equals(other.segmentProfiles))
			return false;
		if (startWithSAP != other.startWithSAP)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
