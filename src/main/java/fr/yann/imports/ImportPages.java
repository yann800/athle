package fr.yann.imports;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ImportPages {

	private static final String URL_EN_WIKIPEDIA = "https://en.wikipedia.org/wiki/List_of_";

	public static void main(String[] args) throws MalformedURLException {
		List<String> urls = new ArrayList<>();

		urls.add("Afghan_records_in_athletics"); // List of Afghan records in athletics">Afghanistan</a>
		urls.add("Albanian_records_in_athletics"); // List of Albanian records in athletics">Albania</a>
		urls.add("Algerian_records_in_athletics"); // List of Algerian records in athletics">Algeria</a>
		urls.add("American_Samoan_records_in_athletics"); // List of American Samoan records in athletics">American Samoa</a>
		urls.add("Andorran_records_in_athletics"); // List of Andorran records in athletics">Andorra</a>
		urls.add("Angolan_records_in_athletics"); // List of Angolan records in athletics">Angola</a>
		urls.add("Anguillian_records_in_athletics"); // List of Anguillian records in athletics">Anguilla</a>
		urls.add("Antiguan_and_Barbudan_records_in_athletics"); // List of Antiguan and Barbudan records in athletics">Antigua and Barbuda</a>
		urls.add("Argentine_records_in_athletics"); // List of Argentine records in athletics">Argentina</a>
		urls.add("Armenian_records_in_athletics"); // List of Armenian records in athletics">Armenia</a>
		urls.add("Aruban_records_in_athletics"); // List of Aruban records in athletics">Aruba</a>
		urls.add("Australian_records_in_athletics"); // List of Australian records in athletics">Australia</a>
		urls.add("Austrian_records_in_athletics"); // List of Austrian records in athletics">Austria</a>
		urls.add("Azerbaijani_records_in_athletics"); // List of Azerbaijani records in athletics">Azerbaijan</a>
		urls.add("Bahamian_records_in_athletics"); // List of Bahamian records in athletics">Bahamas</a>
		urls.add("Bahraini_records_in_athletics"); // List of Bahraini records in athletics">Bahrain</a>
		urls.add("Bangladeshi_records_in_athletics"); // List of Bangladeshi records in athletics">Bangladesh</a>
		urls.add("Barbadian_records_in_athletics"); // List of Barbadian records in athletics">Barbados</a>
		urls.add("Belarusian_records_in_athletics"); // List of Belarusian records in athletics">Belarus</a>
		urls.add("Belgian_records_in_athletics"); // List of Belgian records in athletics">Belgium</a>
		urls.add("Belizean_records_in_athletics"); // List of Belizean records in athletics">Belize</a>
		urls.add("Beninese_records_in_athletics"); // List of Beninese records in athletics">Benin</a>
		urls.add("Bermudian_records_in_athletics"); // List of Bermudian records in athletics">Bermuda</a>
		urls.add("Bhutanese_records_in_athletics"); // List of Bhutanese records in athletics">Bhutan</a>
		urls.add("Bolivian_records_in_athletics"); // List of Bolivian records in athletics">Bolivia</a>
		urls.add("Bosnian_and_Herzegovinian_records_in_athletics"); // List of Bosnian and Herzegovinian records in athletics">Bosnia and Herzegovina</a>
		urls.add("Botswana_records_in_athletics"); // List of Botswana records in athletics">Botswana</a>
		urls.add("Brazilian_records_in_athletics"); // List of Brazilian records in athletics">Brazil</a>
		urls.add("British_Virgin_Islands_records_in_athletics"); // List of British Virgin Islands records in athletics">British Virgin Islands</a>
		urls.add("Bruneian_records_in_athletics"); // List of Bruneian records in athletics">Brunei</a>
		urls.add("Bulgarian_records_in_athletics"); // List of Bulgarian records in athletics">Bulgaria</a>
		urls.add("Burkinab%C3%A9_records_in_athletics"); // List of Burkinabé records in athletics">Burkina Faso</a>
		urls.add("Burundian_records_in_athletics"); // List of Burundian records in athletics">Burundi</a>
		urls.add("Cambodian_records_in_athletics"); // List of Cambodian records in athletics">Cambodia</a>
		urls.add("Cameroonian_records_in_athletics"); // List of Cameroonian records in athletics">Cameroon</a>
		urls.add("Canadian_records_in_athletics"); // List of Canadian records in athletics">Canada</a>
		urls.add("Cape_Verdean_records_in_athletics"); // List of Cape Verdean records in athletics">Cape Verde</a>
		urls.add("Caymanian_records_in_athletics"); // List of Caymanian records in athletics">Cayman Islands</a>
		urls.add("Central_African_Republic_records_in_athletics"); // List of Central African Republic records in athletics">Central African Republic</a>
		urls.add("Chadian_records_in_athletics"); // List of Chadian records in athletics">Chad</a>
		urls.add("Chilean_records_in_athletics"); // List of Chilean records in athletics">Chile</a>
		urls.add("Chinese_records_in_athletics"); // List of Chinese records in athletics">China</a>
		urls.add("Taiwanese_records_in_athletics"); // List of Taiwanese records in athletics">Chinese Taipei</a>
		urls.add("Colombian_records_in_athletics"); // List of Colombian records in athletics">Colombia</a>
		urls.add("Comorian_records_in_athletics"); // List of Comorian records in athletics">Comoros</a>
		urls.add("Democratic_Republic_of_the_Congo_records_in_athletics"); // List of Democratic Republic of the Congo records in athletics">Democratic Republic of the Congo</a>
		urls.add("Republic_of_the_Congo_records_in_athletics"); // List of Republic of the Congo records in athletics">Republic of the Congo</a>
		urls.add("Cook_Islands_records_in_athletics"); // List of Cook Islands records in athletics">Cook Islands</a>
		urls.add("Costa_Rican_records_in_athletics"); // List of Costa Rican records in athletics">Costa Rica</a>
		urls.add("Croatian_records_in_athletics"); // List of Croatian records in athletics">Croatia</a>
		urls.add("Cuban_records_in_athletics"); // List of Cuban records in athletics">Cuba</a>
		urls.add("Cypriot_records_in_athletics"); // List of Cypriot records in athletics">Cyprus</a>
		urls.add("Czech_records_in_athletics"); // List of Czech records in athletics">Czech Republic</a>
		urls.add("Danish_records_in_athletics"); // List of Danish records in athletics">Denmark</a>
		urls.add("Djiboutian_records_in_athletics"); // List of Djiboutian records in athletics">Djibouti</a>
		urls.add("Dominica_records_in_athletics"); // List of Dominica records in athletics">Dominica</a>
		urls.add("Dominican_Republic_records_in_athletics"); // List of Dominican Republic records in athletics">Dominican Republic</a>
		urls.add("East_Timorese_records_in_athletics"); // List of East Timorese records in athletics">East Timor</a>
		urls.add("Ecuadorian_records_in_athletics"); // List of Ecuadorian records in athletics">Ecuador</a>
		urls.add("Egyptian_records_in_athletics"); // List of Egyptian records in athletics">Egypt</a>
		urls.add("Salvadorian_records_in_athletics"); // List of Salvadorian records in athletics">El Salvador</a>
		urls.add("Equatorial_Guinean_records_in_athletics"); // List of Equatorial Guinean records in athletics">Equatorial Guinea</a>
		urls.add("Eritrean_records_in_athletics"); // List of Eritrean records in athletics">Eritrea</a>
		urls.add("Estonian_records_in_athletics"); // List of Estonian records in athletics">Estonia</a>
		urls.add("Ethiopian_records_in_athletics"); // List of Ethiopian records in athletics">Ethiopia</a>
		urls.add("Federated_States_of_Micronesia_records_in_athletics"); // List of Federated States of Micronesia records in athletics">Federated States of Micronesia</a>
		urls.add("Fijian_records_in_athletics"); // List of Fijian records in athletics">Fiji</a>
		urls.add("Finnish_records_in_athletics"); // List of Finnish records in athletics">Finland</a>
		urls.add("French_records_in_athletics"); // List of French records in athletics">France</a>
		urls.add("French_Polynesian_records_in_athletics"); // List of French Polynesian records in athletics">French Polynesia</a>
		urls.add("Gabonese_records_in_athletics"); // List of Gabonese records in athletics">Gabon</a>
		urls.add("Gambian_records_in_athletics"); // List of Gambian records in athletics">Gambia</a>
		urls.add("records_in_athletics_for_Georgia_(country)"); // List of records in athletics for Georgia (country)">Georgia</a>
		
		
		urls.add("German_records_in_athletics"); // List of German records in athletics">Germany</a>
		urls.add("Ghanaian_records_in_athletics"); // List of Ghanaian records in athletics">Ghana</a>
		urls.add("Gibraltarian_records_in_athletics"); // List of Gibraltarian records in athletics">Gibraltar</a>
		urls.add("Greek_records_in_athletics"); // List of Greek records in athletics">Greece</a>
		urls.add("Grenadian_records_in_athletics"); // List of Grenadian records in athletics">Grenada</a>
		urls.add("Guamanian_records_in_athletics"); // List of Guamanian records in athletics">Guam</a>
		urls.add("Guatemalan_records_in_athletics"); // List of Guatemalan records in athletics">Guatemala</a>
		urls.add("Guinean_records_in_athletics"); // List of Guinean records in athletics">Guinea</a>
		urls.add("Bissau-Guinean_records_in_athletics"); // List of Bissau-Guinean records in athletics">Guinea-Bissau</a>
		urls.add("Guyanese_records_in_athletics"); // List of Guyanese records in athletics">Guyana</a>
		urls.add("Haitian_records_in_athletics"); // List of Haitian records in athletics">Haiti</a>
		urls.add("Honduran_records_in_athletics"); // List of Honduran records in athletics">Honduras</a>
		urls.add("Hong_Kong_records_in_athletics"); // List of Hong Kong records in athletics">Hong Kong</a>
		urls.add("Hungarian_records_in_athletics"); // List of Hungarian records in athletics">Hungary</a>
		urls.add("Icelandic_records_in_athletics"); // List of Icelandic records in athletics">Iceland</a>
		urls.add("Indian_records_in_athletics"); // List of Indian records in athletics">India</a>
		urls.add("Indonesian_records_in_athletics"); // List of Indonesian records in athletics">Indonesia</a>
		urls.add("Iranian_records_in_athletics"); // List of Iranian records in athletics">Iran</a>
		urls.add("Iraqi_records_in_athletics"); // List of Iraqi records in athletics">Iraq</a>
		urls.add("Irish_records_in_athletics"); // List of Irish records in athletics">Ireland</a>
		urls.add("Israeli_records_in_athletics"); // List of Israeli records in athletics">Israel</a>
		urls.add("Italian_records_in_athletics"); // List of Italian records in athletics">Italy</a>
		urls.add("Ivorian_records_in_athletics"); // List of Ivorian records in athletics">Ivory Coast</a>
		urls.add("Jamaican_records_in_athletics"); // List of Jamaican records in athletics">Jamaica</a>
		urls.add("Japanese_records_in_athletics"); // List of Japanese records in athletics">Japan</a>
		urls.add("Jordanian_records_in_athletics"); // List of Jordanian records in athletics">Jordan</a>
		urls.add("Kazakhstani_records_in_athletics"); // List of Kazakhstani records in athletics">Kazakhstan</a>
		urls.add("Kenyan_records_in_athletics"); // List of Kenyan records in athletics">Kenya</a>
		urls.add("Kiribati_records_in_athletics"); // List of Kiribati records in athletics">Kiribati</a>
		urls.add("North_Korean_records_in_athletics"); // List of North Korean records in athletics">North Korea</a>
		urls.add("South_Korean_records_in_athletics"); // List of South Korean records in athletics">South Korea</a>
		urls.add("Kosovan_records_in_athletics"); // List of Kosovan records in athletics">Kosovo</a>
		urls.add("Kyrgyzstani_records_in_athletics"); // List of Kyrgyzstani records in athletics">Kyrgyzstan</a>
		urls.add("Kuwaiti_records_in_athletics"); // List of Kuwaiti records in athletics">Kuwait</a>
		urls.add("Laotian_records_in_athletics"); // List of Laotian records in athletics">Laos</a>
		urls.add("Latvian_records_in_athletics"); // List of Latvian records in athletics">Latvia</a>
		urls.add("Lebanese_records_in_athletics"); // List of Lebanese records in athletics">Lebanon</a>
		urls.add("Lesotho_records_in_athletics"); // List of Lesotho records in athletics">Lesotho</a>
		urls.add("Liberian_records_in_athletics"); // List of Liberian records in athletics">Liberia</a>
		urls.add("Libyan_records_in_athletics"); // List of Libyan records in athletics">Libya</a>
		urls.add("Liechtensteinian_records_in_athletics"); // List of Liechtensteinian records in athletics">Liechtenstein</a>
		urls.add("Lithuanian_records_in_athletics"); // List of Lithuanian records in athletics">Lithuania</a>
		urls.add("Luxembourgish_records_in_athletics"); // List of Luxembourgish records in athletics">Luxembourg</a>
		urls.add("Macanese_records_in_athletics"); // List of Macanese records in athletics">Macau</a>
		urls.add("Macedonian_records_in_athletics"); // List of Macedonian records in athletics">Macedonia</a>
		urls.add("Malagasy_records_in_athletics"); // List of Malagasy records in athletics">Madagascar</a>
		urls.add("Malawian_records_in_athletics"); // List of Malawian records in athletics">Malawi</a>
		urls.add("Malaysian_records_in_athletics"); // List of Malaysian records in athletics">Malaysia</a>
		urls.add("Maldivian_records_in_athletics"); // List of Maldivian records in athletics">Maldives</a>
		urls.add("Malian_records_in_athletics"); // List of Malian records in athletics">Mali</a>
		urls.add("Maltese_records_in_athletics"); // List of Maltese records in athletics">Malta</a>
		urls.add("Marshallese_records_in_athletics"); // List of Marshallese records in athletics">Marshall Islands</a>
		urls.add("Mauritanian_records_in_athletics"); // List of Mauritanian records in athletics">Mauritania</a>
		urls.add("Mauritian_records_in_athletics"); // List of Mauritian records in athletics">Mauritius</a>
		urls.add("Mexican_records_in_athletics"); // List of Mexican records in athletics">Mexico</a>
		urls.add("Moldovan_records_in_athletics"); // List of Moldovan records in athletics">Moldova</a>
		urls.add("Mon%C3%A9gasque_records_in_athletics"); // List of Monégasque records in athletics">Monaco</a>
		urls.add("Mongolian_records_in_athletics"); // List of Mongolian records in athletics">Mongolia</a>
		urls.add("Montenegrin_records_in_athletics"); // List of Montenegrin records in athletics">Montenegro</a>
		urls.add("Montserrat_records_in_athletics"); // List of Montserrat records in athletics">Montserrat</a>
		urls.add("Moroccan_records_in_athletics"); // List of Moroccan records in athletics">Morocco</a>
		urls.add("Mozambican_records_in_athletics"); // List of Mozambican records in athletics">Mozambique</a>
		urls.add("Burmese_records_in_athletics"); // List of Burmese records in athletics">Myanmar</a>
		urls.add("Namibian_records_in_athletics"); // List of Namibian records in athletics">Namibia</a>
		urls.add("Nauruan_records_in_athletics"); // List of Nauruan records in athletics">Nauru</a>
		urls.add("Nepalese_records_in_athletics"); // List of Nepalese records in athletics">Nepal</a>
		urls.add("Dutch_records_in_athletics"); // List of Dutch records in athletics">Netherlands</a>
		urls.add("Netherlands_Antillean_records_in_athletics"); // List of Netherlands Antillean records in athletics">Netherlands Antilles</a> (Not a member of IAAF)
		urls.add("New_Caledonian_records_in_athletics"); // List of New Caledonian records in athletics">New Caledonia</a>
		urls.add("New_Zealand_records_in_athletics"); // List of New Zealand records in athletics">New Zealand</a>
		urls.add("Nicaraguan_records_in_athletics"); // List of Nicaraguan records in athletics">Nicaragua</a>
		urls.add("Nigerien_records_in_athletics"); // List of Nigerien records in athletics">Niger</a>
		
		
		
		urls.add("Nigerian_records_in_athletics"); // List of Nigerian records in athletics">Nigeria</a>
		urls.add("Niuean_records_in_athletics"); // List of Niuean records in athletics">Niue</a>
		urls.add("Norfolk_Island_records_in_athletics"); // List of Norfolk Island records in athletics">Norfolk Island</a>
		urls.add("Northern_Mariana_Islands_records_in_athletics"); // List of Northern Mariana Islands records in athletics">Northern Mariana Islands</a>
		urls.add("Norwegian_records_in_athletics"); // List of Norwegian records in athletics">Norway</a>
		urls.add("Omani_records_in_athletics"); // List of Omani records in athletics">Oman</a>
		urls.add("Pakistani_records_in_athletics"); // List of Pakistani records in athletics">Pakistan</a>
		urls.add("Palauan_records_in_athletics"); // List of Palauan records in athletics">Palau</a>
		urls.add("Palestinian_records_in_athletics"); // List of Palestinian records in athletics">Palestine</a>
		urls.add("Panamanian_records_in_athletics"); // List of Panamanian records in athletics">Panama</a>
		urls.add("Papua_New_Guinean_records_in_athletics"); // List of Papua New Guinean records in athletics">Papua New Guinea</a>
		urls.add("Paraguayan_records_in_athletics"); // List of Paraguayan records in athletics">Paraguay</a>
		urls.add("Peruvian_records_in_athletics"); // List of Peruvian records in athletics">Peru</a>
		urls.add("Filipino_records_in_athletics"); // List of Filipino records in athletics">Philippines</a>
		urls.add("Polish_records_in_athletics"); // List of Polish records in athletics">Poland</a>
		urls.add("Portuguese_records_in_athletics"); // List of Portuguese records in athletics">Portugal</a>
		urls.add("Puerto_Rican_records_in_track_and_field"); // List of Puerto Rican records in track and field">Puerto Rico</a>
		urls.add("Qatari_records_in_athletics"); // List of Qatari records in athletics">Qatar</a>
		urls.add("Romanian_records_in_athletics"); // List of Romanian records in athletics">Romania</a>
		urls.add("Russian_records_in_athletics"); // List of Russian records in athletics">Russia</a>
		urls.add("Rwandan_records_in_athletics"); // List of Rwandan records in athletics">Rwanda</a>
		urls.add("Saint_Kitts_and_Nevis_records_in_athletics"); // List of Saint Kitts and Nevis records in athletics">Saint Kitts and Nevis</a>
		urls.add("Saint_Lucian_records_in_athletics"); // List of Saint Lucian records in athletics">Saint Lucia</a>
		urls.add("Vincentian_records_in_athletics"); // List of Vincentian records in athletics">Saint Vincent and the Grenadines</a>
		urls.add("Samoan_records_in_athletics"); // List of Samoan records in athletics">Samoa</a>
		urls.add("Sammarinese_records_in_athletics"); // List of Sammarinese records in athletics">San Marino</a>
		urls.add("Santomean_records_in_athletics"); // List of Santomean records in athletics">São Tomé and Príncipe</a>
		urls.add("Saudi_Arabian_records_in_athletics"); // List of Saudi Arabian records in athletics">Saudi Arabia</a>
		urls.add("Senegalese_records_in_athletics"); // List of Senegalese records in athletics">Senegal</a>
		urls.add("Serbian_records_in_athletics"); // List of Serbian records in athletics">Serbia</a>
		urls.add("Seychellois_records_in_athletics"); // List of Seychellois records in athletics">Seychelles</a>
		urls.add("Sierra_Leonean_records_in_athletics"); // List of Sierra Leonean records in athletics">Sierra Leone</a>
		urls.add("Singaporean_records_in_athletics"); // List of Singaporean records in athletics">Singapore</a>
		urls.add("Slovak_records_in_athletics"); // List of Slovak records in athletics">Slovakia</a>
		urls.add("Slovenian_records_in_athletics"); // List of Slovenian records in athletics">Slovenia</a>
		urls.add("Solomon_Islands_records_in_athletics"); // List of Solomon Islands records in athletics">Solomon Islands</a>
		urls.add("Somalian_records_in_athletics"); // List of Somalian records in athletics">Somalia</a>
		urls.add("South_African_records_in_athletics"); // List of South African records in athletics">South Africa</a>
		urls.add("South_Sudanese_records_in_athletics"); // List of South Sudanese records in athletics">South Sudan</a>
		urls.add("Spanish_records_in_athletics"); // List of Spanish records in athletics">Spain</a>
		urls.add("Sri_Lankan_records_in_athletics"); // List of Sri Lankan records in athletics">Sri Lanka</a>
		urls.add("Sudanese_records_in_athletics"); // List of Sudanese records in athletics">Sudan</a>
		urls.add("Surinamese_records_in_athletics"); // List of Surinamese records in athletics">Suriname</a>
		urls.add("Swazi_records_in_athletics"); // List of Swazi records in athletics">Swaziland</a>
		urls.add("Swedish_records_in_athletics"); // List of Swedish records in athletics">Sweden</a>
		urls.add("Swiss_records_in_athletics"); // List of Swiss records in athletics">Switzerland</a>
		urls.add("Syrian_records_in_athletics"); // List of Syrian records in athletics">Syria</a>
		urls.add("Tajikistani_records_in_athletics"); // List of Tajikistani records in athletics">Tajikistan</a>
		urls.add("Tanzanian_records_in_athletics"); // List of Tanzanian records in athletics">Tanzania</a>
		urls.add("Thai_records_in_athletics"); // List of Thai records in athletics">Thailand</a>
		urls.add("Togolese_records_in_athletics"); // List of Togolese records in athletics">Togo</a>
		urls.add("Tongan_records_in_athletics"); // List of Tongan records in athletics">Tonga</a>
		urls.add("Trinidad_and_Tobago_records_in_athletics"); // List of Trinidad and Tobago records in athletics">Trinidad and Tobago</a>
		urls.add("Tunisian_records_in_athletics"); // List of Tunisian records in athletics">Tunisia</a>
		urls.add("Turkish_records_in_athletics"); // List of Turkish records in athletics">Turkey</a>
		urls.add("Turkmen_records_in_athletics"); // List of Turkmen records in athletics">Turkmenistan</a>
		urls.add("Turks_and_Caicos_Islands_records_in_athletics"); // List of Turks and Caicos Islands records in athletics">Turks and Caicos Islands</a>
		urls.add("Tuvaluan_records_in_athletics"); // List of Tuvaluan records in athletics">Tuvalu</a>
		urls.add("Ugandan_records_in_athletics"); // List of Ugandan records in athletics">Uganda</a>
		urls.add("Ukrainian_records_in_athletics"); // List of Ukrainian records in athletics">Ukraine</a>
		urls.add("Emirati_records_in_athletics"); // List of Emirati records in athletics">United Arab Emirates</a>
		urls.add("British_records_in_athletics"); // List of British records in athletics">United Kingdom</a>
		urls.add("United_States_records_in_track_and_field"); // List of United States records in track and field">United States</a>
		urls.add("United_States_Virgin_Islands_records_in_track_and_field"); // List of United States Virgin Islands records in track and field">United States Virgin Islands</a>
		urls.add("Uruguayan_records_in_athletics"); // List of Uruguayan records in athletics">Uruguay</a>
		urls.add("Uzbekistani_records_in_athletics"); // List of Uzbekistani records in athletics">Uzbekistan</a>
		urls.add("Vanuatuan_records_in_athletics"); // List of Vanuatuan records in athletics">Vanuatu</a>
		urls.add("Venezuelan_records_in_athletics"); // List of Venezuelan records in athletics">Venezuela</a>
		urls.add("Vietnamese_records_in_athletics"); // List of Vietnamese records in athletics">Vietnam</a>
		urls.add("Yemeni_records_in_athletics"); // List of Yemeni records in athletics">Yemen</a>
		urls.add("Zambian_records_in_athletics"); // List of Zambian records in athletics">Zambia</a>
		urls.add("Zimbabwean_records_in_athletics"); // List of Zimbabwean records in athletics">Zimbabwe</a>

		int i = 1;
		for (String url : urls) {
			System.out.println(i + " - " + url.replace("_records_in_athletics", "").replace("_records_in_track_and_field", ""));
			importPage(URL_EN_WIKIPEDIA + url, i++);
		}

	}

	private static void importPage(String url, int num) throws MalformedURLException {
		
		Path p = FileSystems.getDefault().getPath("./record", "pays" + num + ".html");

		URL website = new URL(url);
		try (InputStream in = website.openStream()) {
			Files.copy(in, p, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
