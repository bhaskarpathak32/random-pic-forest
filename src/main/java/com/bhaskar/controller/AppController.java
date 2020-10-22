package com.bhaskar.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bhaskar.beans.FormData;
import com.bhaskar.service.PicService;

@RestController
@RequestMapping("/api")
public class AppController {

	@Autowired
	private PicService service;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bhaskar.controller.TrainNeuralNetwork#generateModel()
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView showpic( @PathVariable("id") String id ) throws IOException, NoSuchFieldException, IllegalAccessException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("result");
          
        FormData data=service.getFormData(id);
        mav.addObject("formData",data );
        return mav;
	}
    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public List<FormData> showpics( ) throws IOException, NoSuchFieldException, IllegalAccessException {
    return service.getPicList();
    }



}