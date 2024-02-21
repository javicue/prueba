package com.javiersan.prueba.controllers;

import io.swagger.annotations.Api; 
import io.swagger.annotations.ApiOperation; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;

import com.javiersan.prueba.models.entity.Nave;
import com.javiersan.prueba.models.service.INaveService;

import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Api(value = "Controlador Naves", description = "Esta API tiene un CRUD para naves")
public class NavesRestController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired 
    private INaveService naveService;

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    @ApiOperation(value = "Lista todas las naves", notes = "retorna un listado paginado de todas las naves")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4); 
   
		
		Page<Nave> naves = naveService.findAll(pageRequest);

		PageRender<Nave> pageRender = new PageRender<Nave>("/listar", naves);  //url donde están los clientes
		model.addAttribute("titulo", "Listado de naves");
		model.addAttribute("naves", naves);
		model.addAttribute("page", pageRender);
		return "listar";
	}

  
    @Secured({"ROLE_USER"})
    @GetMapping("/naves/{id}")
    @ApiOperation(value = "busca una nave por su id", notes = "retorna la nave con el id indicado")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Nave nave = null;
        Map<String, Object> response = new HashMap<>();

        try{

            nave = naveService.findByid(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(nave == null) {
            response.put("mensaje", "El id de la nave: ".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);         
        }
        return new ResponseEntity<Nave>(nave,HttpStatus.OK);
    }

    @GetMapping(value = "/nave/{term}", produces = { "application/json" }) 
    @ApiOperation(value = "Busca las naves que coincidan con el término dado", notes = "retorna en elbody una lista de las naves que contienen ese término ")
	public @ResponseBody List<Nave> cargarNaves(@PathVariable String term) {

		return naveService.findByNombre(term);
	}


    @Secured("ROLE_ADMIN")
    @PostMapping("/naves")
    @ApiOperation(value = "Crea una nave nueba en la base de datos", notes = "retorna un objeto con la response y el httpStatus correspondiente")
    public ResponseEntity<?> create(@Valid @RequestMapping Nave nave, BindinResult result) {

        Nave naveNew = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
               .stream()
               .map(err -> {
                return "El campo '" + err.getField() +"' " + err.getDefaultMessage();
               })
               .collect(Collectors.toList());

               response.put("errors", errors);
               return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            naveNew = naveService.save(nave);

        }catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La nave  ha sido creado con éxito!");
        response.put("nave", naveNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);


    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/naves/{id}")
    @ApiOperation(value = "Editar unna nave de la base de datos", notes = "retorna retorna un objeto con la response y el httpStatus correspondiente ")
    public ResponseEntity<?> updated(@Valid @RequestParam Nave nave, BindingResult result, @PathVariable Long id) {

        Nave naveActual = NaveService.findByid(id);

        Nave naveUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
            .stream()
            .map(err -> {
                return "El campo '" + err.getField() +"' " + err.getDefaultMessage();
            })
            .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(NaveActual == null) {
            response.put("mensaje", "Error: no se pudo editar la nave Id: ".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            naveActual.setNombre(nave.getNombre());
            naveActual.setModelo(nave.getModelo());
            naveActual.setCreateAt(nave.getCreateAt());

            naveUpdated = naveService.save(naveActual);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al actualizar la nave en la base de datos");
            response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La nave ha sido actualizada con éxito!");
        response.put("nave", naveUpdated);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }


    @Secured("ROLE_ADMIN")
    @DeleteMapping("/naves/{id}")
    @ApiOperation(value = "Borra la nave con id pasado por usuario de la base de datos", notes = "retorna una response y el httpStatus correspondiente")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            naveService.delete(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar la nave en la base de datos");
            response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La nave ha sido eliminada con éxito!");

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
    
    
    
}
