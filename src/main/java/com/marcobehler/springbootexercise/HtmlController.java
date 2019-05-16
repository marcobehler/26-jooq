package com.marcobehler.springbootexercise;

import com.marcobehler.springbootexercise.jooq.public_.tables.daos.ArticlesDao;
import com.marcobehler.springbootexercise.jooq.public_.tables.pojos.Articles;
import com.marcobehler.springbootexercise.jooq.public_.tables.records.ArticlesRecord;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.marcobehler.springbootexercise.jooq.public_.tables.Articles.ARTICLES;

@Controller
public class HtmlController {

    @Autowired
    private ArticlesDao articlesDao;

    @Autowired
    private DSLContext dslContext;

    @GetMapping("/")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        model.addAttribute("articles", articlesDao.findAll());
        return "blog";
    }

    @GetMapping("/articles/{id}")
    public String detailView(Model model, @PathVariable Integer id) {

        ArticlesRecord record = dslContext
                .selectFrom(ARTICLES)
                .where(ARTICLES.ID.eq(id))
                .fetchAny();

        model.addAttribute("title", record.getTitle());
        return "blog-detail";
    }



}
