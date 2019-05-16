package com.marcobehler.springbootexercise;

import com.marcobehler.springbootexercise.jooq.public_.tables.daos.ArticlesDao;
import com.marcobehler.springbootexercise.jooq.public_.tables.pojos.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HtmlController {

    @Autowired
    private ArticlesDao articlesDao;

    @GetMapping("/")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        model.addAttribute("articles", articlesDao.findAll());
        return "blog";
    }

    @GetMapping("/articles/{id}")
    public String detailView(Model model, @PathVariable Integer id) {
        Articles article = articlesDao.findById(id);

        model.addAttribute("title", article.getTitle());
        return "blog-detail";
    }



}
