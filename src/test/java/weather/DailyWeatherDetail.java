package weather;

public class DailyWeatherDetail {

	private String time;
	private String minTemp;
	private String maxTemp;
	private String windSpeed;
	private String rainfall;
	private String pressure;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMinTemp() {
		return minTemp;
	}
	public int getMinTempInt() {
		return Integer.parseInt(minTemp);
	}
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}
	public String getMaxTemp() {
		return maxTemp;
	}
	public int getMaxTempInt() {
		return Integer.parseInt(maxTemp);
	}
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getRainfall() {
		return rainfall;
	}
	public int getRainfallInt() {
		return Integer.parseInt(rainfall);
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	public String getPressure() {
		return pressure;
	}
	public int getPressureInt() {
		return Integer.parseInt(pressure);
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public int getWindSpeedInt() {
		return Integer.parseInt(windSpeed);
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	@Override
	public String toString() {
		return "DailyWeatherDetail [time=" + time + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", windSpeed="
				+ windSpeed + ", rainfall=" + rainfall + ", pressure=" + pressure + "]";
	}
	public void roundValuesDown() {
		// remove decimal portion of test data - crude rounding down
		maxTemp = maxTemp.contains(".") ? maxTemp.substring(0, maxTemp.indexOf(".")) : maxTemp;
		minTemp = minTemp.contains(".") ? minTemp.substring(0, minTemp.indexOf(".")) : minTemp;
		windSpeed = windSpeed.contains(".") ? windSpeed.substring(0, windSpeed.indexOf(".")) : windSpeed;
		pressure = pressure.contains(".") ? pressure.substring(0, pressure.indexOf(".")) : pressure;
		rainfall = rainfall.contains(".") ? rainfall.substring(0, rainfall.indexOf(".")) : rainfall;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maxTemp == null) ? 0 : maxTemp.hashCode());
		result = prime * result + ((minTemp == null) ? 0 : minTemp.hashCode());
		result = prime * result + ((pressure == null) ? 0 : pressure.hashCode());
		result = prime * result + ((rainfall == null) ? 0 : rainfall.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((windSpeed == null) ? 0 : windSpeed.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		roundValuesDown();
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyWeatherDetail other = (DailyWeatherDetail) obj;
		if (maxTemp == null) {
			if (other.maxTemp != null)
				return false;
		} else if (!maxTemp.equals(other.maxTemp))
			return false;
		if (minTemp == null) {
			if (other.minTemp != null)
				return false;
		} else if (!minTemp.equals(other.minTemp))
			return false;
		if (pressure == null) {
			if (other.pressure != null)
				return false;
		} else if (!pressure.equals(other.pressure))
			return false;
		if (rainfall == null) {
			if (other.rainfall != null)
				return false;
		} else if (!rainfall.equals(other.rainfall))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (windSpeed == null) {
			if (other.windSpeed != null)
				return false;
		} else if (!windSpeed.equals(other.windSpeed))
			return false;
		return true;
	}
}
