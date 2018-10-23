package me.nvorotnikov.grailstest



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EntityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Entity.list(params), model:[entityInstanceCount: Entity.count()]
    }

    def show(Entity entityInstance) {
        respond entityInstance
    }

    def create() {
        respond new Entity(params)
    }

    @Transactional
    def save(Entity entityInstance) {
        if (entityInstance == null) {
            notFound()
            return
        }

        if (entityInstance.hasErrors()) {
            respond entityInstance.errors, view:'create'
            return
        }

        entityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entity.label', default: 'Entity'), entityInstance.id])
                redirect entityInstance
            }
            '*' { respond entityInstance, [status: CREATED] }
        }
    }

    def edit(Entity entityInstance) {
        respond entityInstance
    }

    @Transactional
    def update(Entity entityInstance) {
        if (entityInstance == null) {
            notFound()
            return
        }

        if (entityInstance.hasErrors()) {
            respond entityInstance.errors, view:'edit'
            return
        }

        entityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Entity.label', default: 'Entity'), entityInstance.id])
                redirect entityInstance
            }
            '*'{ respond entityInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Entity entityInstance) {

        if (entityInstance == null) {
            notFound()
            return
        }

        entityInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Entity.label', default: 'Entity'), entityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entity.label', default: 'Entity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
