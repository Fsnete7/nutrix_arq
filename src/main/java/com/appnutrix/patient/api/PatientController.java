package com.appnutrix.patient.api;

import com.appnutrix.patient.domain.Patient;
import com.appnutrix.patient.domain.PatientFavoriteRecipes;
import com.appnutrix.patient.domain.PatientFavoriteRecipesFK;
import com.appnutrix.recipe.domain.Recipe;
import com.appnutrix.patient.infraestructure.persistance.IPatientFavoriteRecipesRepository;
import com.appnutrix.patient.domain.IPatientService;
import com.appnutrix.recipe.domain.IRecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@Api(tags = "Patient", value = "Servicio Web RESTful de Patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPatientFavoriteRecipesRepository patientFavoriteRecipesRepository;
    @Autowired
    private IRecipeService recipeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Patients", notes = "Método para listar todos los patients")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patients encontrados"),
            @ApiResponse(code = 404, message = "Patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findAll() {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.getAll();
            if (patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Patient por Id", notes = "Método para encontrar un Patient por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient encontrado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> patient = patientService.getById(id);
            if(!patient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patient por username", notes = "Método para encontrar un patient por username")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient encontrado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> findByUsername(@PathVariable("username") String username)
    {
        try{
            Patient patient = patientService.findByUsername(username);
            if(patient == null)
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstName/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Patients por firstname", notes = "Método para encontrar patients por firstname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstName(@PathVariable("firstname") String firstname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstName(firstname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByLastName/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patients por lastname", notes = "Método para encontrar patients por lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByLastName(@PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByLastName(lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstNameAndLastName/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patients por firstname y lastname", notes = "Método para encontrar patients por firstname y lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstNameAndLastName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstNameAndLastName(firstname, lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de patients", notes = "Método para agregar un patient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient agregado"),
            @ApiResponse(code = 404, message = "patient no fue agregado")
    })
    public ResponseEntity<Patient> insertCustomer(@Valid @RequestBody Patient patient)
    {
        try{
            Patient newPatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de patient", notes = "Método que actualiza los datos de un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "patient actualizado"),
            @ApiResponse(code = 404, message = "patient no fue encontrado")
    })
    public ResponseEntity<Patient> updatePatient(
            @PathVariable("id") Integer id, @Valid @RequestBody Patient patient){
        try {
            Optional<Patient> foundPatient = patientService.getById(id);
            if(!foundPatient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patient.setId(id);
            patientService.save(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un patient", notes = "Método para eliminar un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "patient eliminado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> deleteCustomer(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> deletedCustomer = patientService.getById(id);
            if(!deletedCustomer.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patientService.delete(id);
            return new ResponseEntity<Patient>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{recipe_id}/{patient_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adición de Recipe favorita a la lista de favoritos de un patient", notes = "Método que añade una Recipe favorita a la lista de favoritos de un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Recipe añadida a la lista de favoritos del patient"),
            @ApiResponse(code = 404, message = "Recipe o patient no encontrado")
    })
    public ResponseEntity<PatientFavoriteRecipes> addFavoriteRecipe(@PathVariable("recipe_id") Integer recipe_id,
                                                                   @PathVariable("patient_id") Integer patient_id,
                                                                   @Valid @RequestBody String date){
        try {
            Date currentDate = ParseDate(date);
            Optional<Recipe> foundRecipe = recipeService.getById(recipe_id);
            Optional<Patient> foundPatient = patientService.getById(patient_id);
            if(!foundPatient.isPresent())
                return new ResponseEntity<PatientFavoriteRecipes>(HttpStatus.NOT_FOUND);
            if(!foundRecipe.isPresent())
                return new ResponseEntity<PatientFavoriteRecipes>(HttpStatus.NOT_FOUND);
            PatientFavoriteRecipesFK newFKS = new PatientFavoriteRecipesFK(patient_id, recipe_id);
            PatientFavoriteRecipes patientFavoriteRecipes = new PatientFavoriteRecipes(newFKS, foundPatient.get(), foundRecipe.get(), currentDate);

            patientFavoriteRecipesRepository.save(patientFavoriteRecipes);
            return ResponseEntity.status(HttpStatus.CREATED).body(patientFavoriteRecipes);
        }catch (Exception e){
            return new ResponseEntity<PatientFavoriteRecipes>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findpatientFavoriteRecipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recipes favoritos de un patient", notes = "Método para listar Recipes favoritos de un patients")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recipes encontrados"),
            @ApiResponse(code = 404, message = "Recipes no encontrados")
    })
    public ResponseEntity<List<Recipe>> findPatientFavoriteRecipes(@PathVariable("id") Integer id)
    {
        try {
            List<Recipe> recipes = new ArrayList<>();
            recipes = patientFavoriteRecipesRepository.findByPatient(id);
            if(recipes.isEmpty())
                return new ResponseEntity<List<Recipe>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Recipe>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{recipe_id}/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Recipe de la lista de favoritos de un patient", notes = "Método para eliminar un Recipe de la lista de favoritos de un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Recipe eliminado"),
            @ApiResponse(code = 404, message = "Recipe no encontrado")
    })
    public ResponseEntity<Patient> deletePatientFavoriteRecipe(@PathVariable("recipe_id") Integer recipe_id,
                                                             @PathVariable("patient_id") Integer patient_id)
    {
        try{
            PatientFavoriteRecipesFK newFKS = new PatientFavoriteRecipesFK(patient_id, recipe_id);
            Optional<PatientFavoriteRecipes> patientFavoriteRecipes = patientFavoriteRecipesRepository.findById(newFKS);
            if(!patientFavoriteRecipes.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patientFavoriteRecipesRepository.delete(patientFavoriteRecipes.get());
            return new ResponseEntity<Patient>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }

}