package dockarium.controller

import com.greencatsoft.angularjs.{inject, Controller}
import com.greencatsoft.angularjs.core.Scope
import dockarium.WebsocketConnectionService

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

/**
 * Created by becker on 2/19/15.
 */
object AdminCtrl extends Controller {

  @JSExportAll
  case class DockerHost(name: String, host: String, port: Int)

  @JSExportAll
  case class R(command: String, payload: DockerHost)


  @inject
  var websocketConnectionService: WebsocketConnectionService = _

  override def initialize(): Unit = {
    super.initialize()

    scope.dockerhost = DockerHost("localhost", "localhost", 2375)

    scope.dynamic.saveDockerConnection = () => {
      println("save docker connection")
      websocketConnectionService.send(R("saveDockerConnection", scope.dockerhost))
    }

  }

  override type ScopeType = AdminCtrlScope

  trait AdminCtrlScope extends Scope {
    var dockerhost: DockerHost = js.native
  }
}



/*
<h2>Admin</h2>
<h3>Add Connection</h3>

<div class="row">

    <div class="col-md-4">

        <div class="form-group">
            <form novalidate class="css-form" ng-controller="AdminCtrl">

                <label class="col-sm-2 control-label" for="name">(Unique) Name:</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" ng-model="dockerhost.name"/><br/>
                </div>

                <label class="col-sm-2 control-label" for="host">Host/Ip:</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" id="host" ng-model="dockerhost.host"/><br/>
                </div>

                <label class="col-sm-2 control-label" for="port">Port:</label>

                <div class="col-sm-10">
                     <input type="number" class="form-control" id="port" ng-model="dockerhost.port" min="1" max="65535"/><br/>
                </div>

                <button type="submit" class="btn btn-default" ng-click="saveDockerConnection()">Save</button>
            </form>
        </div>
    </div>
</div>

 */