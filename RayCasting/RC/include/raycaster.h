#ifndef RAYCASTER_H
#define RAYCASTER_H

#include "scene/scene.h"

namespace rc {
class RayCaster
{
public:
    RayCaster(Scene *s);
    ~RayCaster();

private:
    Scene* scene;
};
}

#endif // RAYCASTER_H
