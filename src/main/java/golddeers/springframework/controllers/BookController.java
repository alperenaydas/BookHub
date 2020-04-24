package golddeers.springframework.controllers;

import golddeers.springframework.commands.BookForm;
import golddeers.springframework.converters.BookToBookForm;
import golddeers.springframework.model.Book;
import golddeers.springframework.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BookController {

    private BookService bookService;

    private BookToBookForm bookToBookForm;

    @Autowired
    public void setBookToBookForm(BookToBookForm bookToBookForm) {
        this.bookToBookForm = bookToBookForm;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/book/list";
    }

    @RequestMapping({"/book/list", "/book"})
    public String listBooks(Model model){
        model.addAttribute("books", bookService.listAll());
        return "book/list";
    }

    @RequestMapping("/book/show/{id}")
    public String getBook(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.getById(Long.valueOf(id)));
        return "book/show";
    }

    @RequestMapping("book/edit/{id}")
    public String edit(@PathVariable String id, Model model){
    	Book book = bookService.getById(Long.valueOf(id));
        BookForm bookForm = bookToBookForm.convert(book);

        model.addAttribute("bookForm", bookForm);
        return "book/bookform";
    }

    @RequestMapping("/book/new")
    public String newBook(Model model){
        model.addAttribute("bookForm", new BookForm());	
        return "book/bookform";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String saveOrUpdateBook(@Valid BookForm bookForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "book/bookform";
        }

        Book savedBook = bookService.saveOrUpdateBookForm(bookForm);

        return "redirect:/book/show/" + savedBook.getId();
    }

    @RequestMapping("/book/delete/{id}")
    public String delete(@PathVariable String id){
        bookService.delete(Long.valueOf(id));
        return "redirect:/book/list";
    }
}
