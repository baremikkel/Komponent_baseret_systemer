import dk.sdu.mmmi.cbse.common.services.*;

module Common {
    uses IEntityProcessingService;
    uses IGamePluginService;
    uses IPostEntityProcessingService;


    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.services;

    exports dk.sdu.mmmi.cbse.common.data.entityparts;
}