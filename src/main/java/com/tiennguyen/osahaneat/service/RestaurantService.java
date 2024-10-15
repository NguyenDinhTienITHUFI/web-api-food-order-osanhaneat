package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.dto.CategoryDTO;
import com.tiennguyen.osahaneat.dto.MenuDTO;
import com.tiennguyen.osahaneat.dto.RestaurantDTO;
import com.tiennguyen.osahaneat.entity.Food;
import com.tiennguyen.osahaneat.entity.MenuRestaurant;
import com.tiennguyen.osahaneat.entity.RatingRestaurant;
import com.tiennguyen.osahaneat.entity.Restaurant;
import com.tiennguyen.osahaneat.repository.RestaurantRepository;
import com.tiennguyen.osahaneat.service.imp.FIleServiceImp;
import com.tiennguyen.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    FIleServiceImp fIleServiceImp;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship, String address, String openDate) {
       boolean isInsertSuccess=false;
        try{
            boolean isSaveFileSuccess= fIleServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant=new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setIffreeship(isFreeship);
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date open=simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(open);

                restaurantRepository.save(restaurant);
                isInsertSuccess=true;
            }
        }

        catch(Exception e) {
            System.out.println("Error insert Restaurant "+e.getMessage());

        }
        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> listDto=new ArrayList<>();
        PageRequest pageRequest=PageRequest.of(0,6);
        Page<Restaurant> listData= restaurantRepository.findAll(pageRequest);
        for(Restaurant data:listData)
        {
            RestaurantDTO restaurantDTO=new RestaurantDTO();
            restaurantDTO.setId(data.getId());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setFreeship(data.getIffreeship());
            restaurantDTO.setDesc((data.getDescription()));
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            listDto.add(restaurantDTO);
        }
        return listDto;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant= restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO=new RestaurantDTO();
        if(restaurant.isPresent())
        {
            List<CategoryDTO> categoryDTOList=new ArrayList<>();
            Restaurant restaurantData=restaurant.get();

            restaurantDTO.setTitle(restaurantData.getTitle());
            restaurantDTO.setImage(restaurantData.getImage());
            restaurantDTO.setSubtitle(restaurantData.getSubtitle());
            restaurantDTO.setRating(calculatorRating(restaurantData.getListRatingRestaurant()));
            restaurantDTO.setFreeship(restaurantData.getIffreeship());
            restaurantDTO.setOpenDate(restaurantData.getOpenDate());
            restaurantDTO.setDesc(restaurantData.getDescription());
            for (MenuRestaurant menuRestaurant:restaurantData.getListMenuRestaurant()){
                List<MenuDTO> menuDTOS=new ArrayList<>();
                CategoryDTO categoryDTO=new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());
                for(Food food:menuRestaurant.getCategory().getListFood()){
                    MenuDTO menuDTO=new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setIs_freeship(food.isIs_freeship());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setPrice(food.getPrice());
                    menuDTO.setDescription(food.getDescription());
                    menuDTO.setId(food.getId());
                    menuDTOS.add(menuDTO);
                }
                categoryDTO.setMenu(menuDTOS);
                categoryDTOList.add(categoryDTO);

            }
            restaurantDTO.setCategorys(categoryDTOList);


        }
        return restaurantDTO;
    }

    private double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint=0;
        for(RatingRestaurant restaurant:listRating)
        {
            totalPoint +=restaurant.getRatePoint();
        }
        return totalPoint/listRating.size();
    }
}
