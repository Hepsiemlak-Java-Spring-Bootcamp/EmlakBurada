package emlakburada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClient;
import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdvertService {

	@Autowired
	private AdvertRepository advertRepository;

	private static int advertNo = 38164784;

	@Autowired
	private BannerClient bannerClient;

	@Autowired
	private QueueService queueService;

	@Autowired
	private UserRepository userRepository;

	// @Autowired
//	public IlanService(IlanRepository ilanRepository) {
//		super();
//		this.ilanRepository = ilanRepository;
//	}

	public List<AdvertResponse> getAllAdverts() {
		// System.out.println("IlanService -> ilanRepository: " +
		// advertRepository.toString());
		// kullaniciService.getAllKullanici();
		List<AdvertResponse> advertList = new ArrayList<>();
		for (Advert advert : advertRepository.findAll()) {
			advertList.add(convertToAdvertResponse(advert));
		}
		return advertList;
	}

	public AdvertResponse saveAdvert(AdvertRequest request) {

		Optional<User> foundUser = userRepository.findById(request.getUserId());

		Advert advert = convertToAdvert(request, foundUser);

		try {

			if (advert == null) {
				throw new Exception("İlan kaydedilemedi");
			}

			Advert savedAdvert = advertRepository.save(advert);
			// EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
			// queueService.sendMessage(emailMessage);
			// bannerClient.saveBanner();
			return convertToAdvertResponse(savedAdvert);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return null;

	}

	private AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
		AdvertResponse response = new AdvertResponse();
		response.setBaslik(savedAdvert.getBaslik());
		response.setFiyat(savedAdvert.getFiyat());
		response.setAdvertNo(savedAdvert.getAdvertNo());
		response.setKullanici(savedAdvert.getCreatorUser());
		return response;
	}

	private Advert convertToAdvert(AdvertRequest request, Optional<User> foundUser) {
		// Advert advert = new Advert(new RealEstate(), new User(), new String[5]);

		Advert advert = null;

		if (foundUser.isPresent()) {
			advert = new Advert();
			advert.setCreatorUser(foundUser.get());
			advertNo++;

			advert.setAdvertNo(advertNo);
			advert.setBaslik(request.getBaslik());
			advert.setFiyat(request.getFiyat());
		} else {
			log.info("Kullanıcı Bulunamadı!");
		}

		return advert;
	}

	public AdvertResponse getAdvertByAdvertId(int advertId) {
		Optional<Advert> advert = advertRepository.findById(advertId);
		return convertToAdvertResponse(advert.get());
	}

}
