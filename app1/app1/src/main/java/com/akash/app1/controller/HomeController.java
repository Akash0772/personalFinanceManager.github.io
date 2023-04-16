package com.akash.app1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.akash.app1.entity.Expense;
import com.akash.app1.entity.Income;
import com.akash.app1.entity.Report;
import com.akash.app1.repository.ExpenseRepository;
import com.akash.app1.repository.IncomeRepository;
import com.akash.app1.repository.ReportRepository;

@Controller
public class HomeController {
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Income> incomes = incomeRepository.findAll();
		List<Expense> expenses = expenseRepository.findAll();
		model.addAttribute("incomes", incomes);
		model.addAttribute("expenses", expenses);
		return "home";
	}
	
	@PostMapping("/add-income")
    public String addIncome(@ModelAttribute("income") Income income) {
        incomeRepository.save(income);
        return "redirect:/";
    }

    @PostMapping("/add-expense")
    public String addExpense(@ModelAttribute("expense") Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/";
    }

    @GetMapping("/reports")
    public String viewReports(Model model) {
        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);
        return "reports";
    }

    @PostMapping("/generate-report")
    public String generateReport(@ModelAttribute("report") Report report, Model model) {
        List<Income> incomes = incomeRepository.findByDateBetween(report.getStartDate(), report.getEndDate());
        List<Expense> expenses = expenseRepository.findByDateBetween(report.getStartDate(), report.getEndDate());
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double netIncome = totalIncome - totalExpenses;
        reportRepository.save(report);
        model.addAttribute("incomes", incomes);
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("netIncome", netIncome);
        return "report";
    }
}

