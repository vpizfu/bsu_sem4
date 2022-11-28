import random


class ElepticalCurve:
    def __init__(self, m, a, b):
        self.m = m
        self.a = a
        self.b = b

    def res(self, _x: int):
        return self.find_sqrt((_x ** 3 + self.a * _x + self.b) % self.m)

    def find_sqrt(self, y_square: int):
        for _y in range(self.m):
            if (_y * _y) % self.m == y_square:
                return [_y] if _y == 0 else [_y, self.get_neg(_y)]
        return []

    def get_neg(self, _x: int):
        return self.m - _x


def get_point_from_eleptic_curve():
    curve = ElepticalCurve(61, 1, 0)
    points = []
    for x in range(curve.m):
        y = curve.res(x)
        points.extend(zip([x, x], y))
    group_order = len(points) + 1
    selected_point = random.randint(0, len(points) - 1)
    print(f'All points: {points}')
    print(f'Group order: {group_order}')
    print(f'Selected point: {points[selected_point]}')
    return selected_point


if __name__ == '__main__':
    get_point_from_eleptic_curve()