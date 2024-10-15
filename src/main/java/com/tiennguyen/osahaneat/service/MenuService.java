package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.entity.Category;
import com.tiennguyen.osahaneat.entity.Food;
import com.tiennguyen.osahaneat.entity.Restaurant;
import com.tiennguyen.osahaneat.repository.FoodRepository;
import com.tiennguyen.osahaneat.service.imp.FIleServiceImp;
import com.tiennguyen.osahaneat.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    FIleServiceImp fIleServiceImp;

    @Autowired
    FoodRepository foodRepository;
    @Override
    public boolean createMenu(MultipartFile file, String title, String is_freeship, String time_ship, double price, int cate_id) {


        boolean isInsertSuccess=false;
        try{
            boolean isSaveFileSuccess= fIleServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food=new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(time_ship);
                food.setPrice(price);

                Category category=new Category();
                category.setId(cate_id);
                food.setCategory(category);

                foodRepository.save(food);

                isInsertSuccess=true;
            }
        }

        catch(Exception e) {
            System.out.println("Error insert Restaurant "+e.getMessage());

        }
        return isInsertSuccess;
    }
}
