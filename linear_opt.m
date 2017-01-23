function [x, fval] = linear_opt(VN, ALPHA, BETA, VMax, Emin, VUmin, VUMax, VEMax,  VDMax)
%VN is V_{n,i} vector
%ALPHA, BETA   
%VMax, Emin
%VUmin is V_{u, i}^{min} vector
%VUMax is V_{u, i}^{max} vector
%VEMax is V_{e, i}^{max} vector
%VDMax is V_{D, i}^{max} vector

%���ǰ���V_{D, i}, V_{e, i}, V_{u, i}, dV_{i}
%��˳�����б���

n = length(VN);

%�����仯���½�
VALPHA = VMax.*ALPHA;
VBETA = VMax.*BETA;
%���첻��Լ��A��B
% \sum V_{e, i} >= E_{min}��ת��ΪС������
A = zeros(1, 4 * n);
for i = n + 1 : 2 * n
    A(i) = -1;
end

b = -Emin;

%�����ʽԼ��
%������Ĺ�ϵԼ�������������Ϊ4n������Ϊn
%i = 1�������
Aeq = zeros(n, 4 * n);
%V_{D, 1}
Aeq(1, 1) = 1;
%V_{e, 1}
Aeq(1, n + 1) = 1;
%V_{u, 1}
Aeq(1, 2 * n + 1) = 1;
%dV_{1}
Aeq(1, 3 * n + 1) = 1;
for i = 2 : n
    %V_{D, i - 1}, V_{D, i}
    Aeq(i, i - 1) = -1;
    Aeq(i, i) = 1;
    %V_{e, i - 1}, V_{e, i}
    Aeq(i, n + i - 1) = -1;
    Aeq(i, n + i) = 1;
    %V_{u, i}
    Aeq(i, 2 * n + i) = 1;
    %dV_{i}
    Aeq(i, 3 * n + i) = 1;
end

beq = VN;

%�������½�Լ��
%�½�
%��ˮ��Լ��������Լ����������ˮԼ��, ��Ա仯Լ��
lb = [zeros(1, n) zeros(1, n) VUmin VALPHA];
%�Ͻ�
ub = [VDMax VEMax VUMax VBETA];

%����linprog
f = zeros(1, 4 * n);
f(n) = 1;
[x, fval] = linprog(f, A, b, Aeq, beq, lb, ub)

end

