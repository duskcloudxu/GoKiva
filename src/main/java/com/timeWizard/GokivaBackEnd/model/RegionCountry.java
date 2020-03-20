package com.timeWizard.GokivaBackEnd.model;

public class RegionCountry {
	protected String RegionCountryId;
	protected String Region;
	protected String Country;
	protected String CountryCode;
	public RegionCountry(String regionCountryId, String region, String country, String countryCode) {
		super();
		RegionCountryId = regionCountryId;
		Region = region;
		Country = country;
		CountryCode = countryCode;
	}
	public String getRegionCountryId() {
		return RegionCountryId;
	}
	public void setRegionCountryId(String regionCountryId) {
		RegionCountryId = regionCountryId;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}



}
