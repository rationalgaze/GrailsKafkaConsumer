//class UrlMappings {
//
//	static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
//
//        "/"(view:"/index")
//        "500"(view:'/error')
//	}
//}

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
        "/" (controller:"KafkaConnect", action:"index")
        "500"(view:'/error')
    }
}

//class UrlMappings {
//    static mappings = {
//        "/$controller/$action?/$id?"{
//            constraints {
//                // apply constraints here
//            }
//        }
//        "/" (controller:"chat", action:"index")
//        "500"(view:'/error')
//    }
//}