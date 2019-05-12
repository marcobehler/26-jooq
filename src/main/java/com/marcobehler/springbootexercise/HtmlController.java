package com.marcobehler.springbootexercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class HtmlController {

    private List<Article> db = Arrays.asList(new Article(1, "Marco", LocalDate.now(), "This is my first great blog post!", "Java and Kotlin"),
            new Article(2, "Marco", LocalDate.now().minusDays(5), "This is my second blog post", "Java & JooQ"));

    @GetMapping("/")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        model.addAttribute("articles", db);
        return "blog";
    }
}
