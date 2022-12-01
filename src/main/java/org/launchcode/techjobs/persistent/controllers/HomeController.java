package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

// several of the methods in HomeController are missing code because the class has not yet been wired with the data layer yet.
//Add a field employerRepository annotated with @Autowired

//Add the employer data from employerRepository into the form template.
//The add job form already includes an employer selection option. Be sure your variable name for the employer data matches that already used in templates/add.

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        List jobs = (List<Job>) jobRepository.findAll();
        model.addAttribute("jobs", jobs );

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());

        List employers = (List<Employer>) employerRepository.findAll();
        model.addAttribute("employers", employers);

        //Then, to get the skills data from a list of ids (rather than a single id as we did with employer),
        // use the CrudRepository method .findAllById(ids).
        List skills = (List<Skill>) skillRepository.findAll();
        model.addAttribute("skills", skills);

        return "add";
    }

    //You’ll need to pass in that attribute value to processAddJobForm in HomeController as a @RequestParam.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            List employers = (List<Employer>) employerRepository.findAll();
            model.addAttribute("employers", employers);


            return "add";
        }

        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            newJob.setEmployer(employer);

        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);


        return "redirect:./";
    }


    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId);
        if(optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        }
        else {
            return "redirect:./";
        }

    }


}

