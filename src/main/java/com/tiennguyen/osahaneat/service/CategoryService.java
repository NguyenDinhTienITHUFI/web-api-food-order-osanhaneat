package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.dto.CategoryDTO;
import com.tiennguyen.osahaneat.dto.MenuDTO;
import com.tiennguyen.osahaneat.entity.Category;
import com.tiennguyen.osahaneat.entity.Food;
import com.tiennguyen.osahaneat.repository.CategoryRepository;
import com.tiennguyen.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest=PageRequest.of(0,3, Sort.by("id"));
        Page<Category> categoryList=categoryRepository.findAll(pageRequest);
        List<CategoryDTO>categoryDTOList=new ArrayList<>();
        for(Category data:categoryList)
        {
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setName(data.getNameCate());
            List<MenuDTO>menuDTOS=new ArrayList<>();
            for(Food dataF:data.getListFood())
            {
                MenuDTO menuDTO=new MenuDTO();
                menuDTO.setTitle(dataF.getTitle());
                menuDTO.setIs_freeship(dataF.isIs_freeship());
                menuDTO.setImage(dataF.getImage());

                menuDTOS.add(menuDTO);

            }
            categoryDTO.setMenu(menuDTOS);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
}
