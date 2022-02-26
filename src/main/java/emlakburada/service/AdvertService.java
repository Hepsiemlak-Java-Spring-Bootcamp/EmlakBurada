package emlakburada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClientFeign;
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
public class AdvertService extends AdvertBaseService {

	@Autowired
	private AdvertRepository advertRepository;

	// @Autowired
	// private BannerClient bannerClient;

	@Autowired
	private BannerClientFeign bannerClientFeign;

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
			
			bannerClientFeign.saveBanner(prepareSaveBannerRequest());
			return convertToAdvertResponse(savedAdvert);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return null;

	}

	public AdvertResponse getAdvertByAdvertId(int advertId) {
		Optional<Advert> advert = advertRepository.findById(advertId);
		return convertToAdvertResponse(advert.get());
	}

}
