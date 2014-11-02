package regalowl.hyperconomy.inventory;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import regalowl.hyperconomy.HC;

public class HPotionEffect extends SerializableObject implements Serializable {
	
	private static final long serialVersionUID = 1194773802989404854L;

	private String potionEffectType;
	private int amplifier;
	private int duration;
	private boolean isAmbient;
 
	
	public HPotionEffect(String potionEffectType, int amplifier, int duration, boolean isAmbient) {
		this.potionEffectType = potionEffectType;
		this.amplifier = amplifier;
		this.duration = duration;
		this.isAmbient = isAmbient;
    }

	public HPotionEffect(String base64String) {
    	try {
			byte[] data = Base64Coder.decode(base64String);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			Object o = ois.readObject();
			ois.close();
			if (!(o instanceof HPotionEffect)) {return;}
			HPotionEffect spe = (HPotionEffect)o;
			this.potionEffectType = spe.getType();
			this.amplifier = spe.getAmplifier();
			this.duration = spe.getDuration();
			this.isAmbient = spe.isAmbient();
    	} catch (Exception e) {
    		HC.hc.getDataBukkit().writeError(e);
    	}
    }

	public String getType() {
		return potionEffectType;
	}
	public int getAmplifier() {
		return amplifier;
	}
	public int getDuration() {
		return duration;
	}
	public boolean isAmbient() {
		return isAmbient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amplifier;
		result = prime * result + duration;
		result = prime * result + (isAmbient ? 1231 : 1237);
		result = prime * result + ((potionEffectType == null) ? 0 : potionEffectType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HPotionEffect other = (HPotionEffect) obj;
		if (amplifier != other.amplifier)
			return false;
		if (duration != other.duration)
			return false;
		if (isAmbient != other.isAmbient)
			return false;
		if (potionEffectType == null) {
			if (other.potionEffectType != null)
				return false;
		} else if (!potionEffectType.equals(other.potionEffectType))
			return false;
		return true;
	}
	

	
}